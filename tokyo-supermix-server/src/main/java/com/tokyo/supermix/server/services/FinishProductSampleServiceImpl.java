package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.FinishProductSampleRequestDto;
import com.tokyo.supermix.data.dto.FinishProductSampleResponseDto;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.QFinishProductSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class FinishProductSampleServiceImpl implements FinishProductSampleService {
  @Autowired
  FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public boolean isFinishProductCodeExist(String code) {
    return finishProductSampleRepository.existsByFinishProductCode(code);
  }

  @Transactional()
  public void saveFinishProductSample(FinishProductSample finishProductSample) {
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (finishProductSample.getCode() == null) {
      String rawMaterialName = mixDesignRepository
          .getOne(finishProductSample.getMixDesign().getCode()).getRawMaterial().getName();
      String codePrefix = " ";
      if (finishProductSample.getWorkOrderNumber() == null) {
        codePrefix = rawMaterialName + "-PP-";
      } else {
        codePrefix = rawMaterialName + "-PO-";
      }
      mixDesign.setCheckDepend(true);
      List<FinishProductSample> finishProductSampleList =
          finishProductSampleRepository.findByCodeContaining(codePrefix);
      if (finishProductSampleList.size() == 0) {
        finishProductSample.setCode(codePrefix + String.format("%04d", 1));
      } else {
        finishProductSample.setCode(
            codePrefix + String.format("%04d", maxNumberFromCode(finishProductSampleList) + 1));
      }
    }
    finishProductSample.setStatus(Status.NEW);
    finishProductSample.setFinishProductCode(finishProductSample.getCode());
    FinishProductSample finishProductSampleObj =
        finishProductSampleRepository.save(finishProductSample);
    if (finishProductSampleObj != null) {
      emailNotification.sendFinishProductSampleEmail(finishProductSampleObj);
    }
  }

  @Transactional()
  public void updateFinishProductSample(FinishProductSample finishProductSample) {
    finishProductSampleRepository.save(finishProductSample);
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductSample> finishProductSampleList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductSampleList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.lastIndexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getAllFinishProductSamples() {
    return finishProductSampleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductSampleExist(String code) {
    return finishProductSampleRepository.existsById(code);
  }

  @Transactional(readOnly = true)
  public FinishProductSample getFinishProductSampleById(String code) {
    return finishProductSampleRepository.findById(code).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductSample(String code) {
    FinishProductSample finishProductSample = finishProductSampleRepository.findById(code).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (finishProductSampleRepository.findByMixDesignCode(mixDesign.getCode()).size() == 1) {
      mixDesign.setCheckDepend(false);
      mixDesignRepository.save(mixDesign);
    }
    finishProductSampleRepository.deleteById(code);
  }

  public boolean isUpdatedFinishProductCodeExist(String code, String finishProductCode) {
    if ((!getFinishProductSampleById(code).getFinishProductCode().equals(finishProductCode))
        && (isFinishProductCodeExist(finishProductCode))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignCodeExist(String code) {
    return finishProductSampleRepository.existsByMixDesignCode(code);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByMixDesignCode(String mixDesignCode) {
    return finishProductSampleRepository.findByMixDesignCode(mixDesignCode);
  }

  @Transactional(readOnly = true)
  public Page<FinishProductSample> searchFinishProductSample(Predicate predicate, int page,
      int size) {
    return finishProductSampleRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByPlantCode(String plantCode,
      Pageable pageable) {
    return finishProductSampleRepository
        .findByWorkOrderNumberNullAndMixDesignPlantCodeOrderByUpdatedAtDesc(plantCode, pageable)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleByStatus(Status status) {
    return finishProductSampleRepository.findByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductSampleStatusExist(Status status) {
    return finishProductSampleRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getAllFinishProductSamplesByPlant(UserPrincipal currentUser,
      Pageable pageable) {
    return finishProductSampleRepository
        .findByWorkOrderNumberNullAndMixDesignPlantCodeInOrderByUpdatedAtDesc(
            currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
                PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE),
            pageable)
        .toList();
  }

  @Transactional(readOnly = true)
  public Long getCountFinishProductSample() {
    return finishProductSampleRepository.countByWorkOrderNumberNull();
  }

  @Transactional(readOnly = true)
  public Long getCountFinishProductSampleByPlantCode(String plantCode) {
    return finishProductSampleRepository.countByMixDesignPlantCodeAndWorkOrderNumberNull(plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSampleResponseDto> searchFinishProductSample(
      BooleanBuilder booleanBuilder, String finishProductCode, String equipmentName,
      String mixDesignCode, String plantName, String plantCode, Status status, String date,
      String code, String rawMaterialName, String workOrderNumber, String customer,
      Pageable pageable, Pagination pagination) {
    if (finishProductCode != null && !finishProductCode.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.finishProductCode
          .contains(finishProductCode));
    }
    if (equipmentName != null && !equipmentName.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.plantEquipment.serialNo
          .contains(equipmentName));
    }
    if (mixDesignCode != null && !mixDesignCode.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.mixDesign.code
          .contains(mixDesignCode));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.mixDesign.plant.name
          .contains(plantName));
    }

    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.mixDesign.plant.code
          .contains(plantCode));
    }
    if (status != null) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.status.eq(status));
    }
    if (date != null) {
      booleanBuilder.and(
          QFinishProductSample.finishProductSample.date.stringValue().contains(date));
    }
    if (code != null && !code.isEmpty()) {
      booleanBuilder.and(
          QFinishProductSample.finishProductSample.code.stringValue().contains(code));
    }
    if (rawMaterialName != null && !rawMaterialName.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.mixDesign.rawMaterial.name
          .stringValue().contains(rawMaterialName));
    }
    if (workOrderNumber != null && !workOrderNumber.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.workOrderNumber.stringValue()
          .stringValue().contains(workOrderNumber));
    }
    if (customer != null && !customer.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.project.customer.name
          .stringValue().stringValue().contains(customer));
    }
    pagination.setTotalRecords(
        (long) ((List<FinishProductSample>) finishProductSampleRepository.findAll(booleanBuilder)).stream()
            .filter(sample -> sample.getWorkOrderNumber() == null)
            .collect(Collectors.toList()).size());
    return mapper.map(
        ((List<FinishProductSample>) finishProductSampleRepository.findAll(booleanBuilder)).stream()
            .filter(sample -> sample.getWorkOrderNumber() == null).collect(Collectors.toList()),
        FinishProductSampleResponseDto.class);
  }

  @Transactional(readOnly = true)
  public Long getSubCategoryCountFinishProductSample(Long materialSubCategoryId) {
    return finishProductSampleRepository
        .countByMixDesignRawMaterialMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public Long getCountSubCategoryFinishProductSampleByPlantCode(String plantCode,
      Long materialSubCategoryId) {
    return finishProductSampleRepository
        .countByMixDesignPlantCodeAndMixDesignRawMaterialMaterialSubCategoryId(plantCode,
            materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public Long getCategoryCountFinishProductSample(Long materialCategoryId) {
    return finishProductSampleRepository
        .countByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(materialCategoryId);
  }

  @Transactional(readOnly = true)
  public Long getCountCategoryFinishProductSampleByPlantCode(String plantCode,
      Long materialCategoryId) {
    return finishProductSampleRepository
        .countByMixDesignPlantCodeAndMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(
            plantCode, materialCategoryId);
  }

  @Transactional(readOnly = true)
  public Long getRawMaterialCountFinishProductSample(Long rawMaterialId) {
    return finishProductSampleRepository.countByMixDesignRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public Long getCountRawMaterialFinishProductSampleByPlantCode(String plantCode,
      Long rawMaterialId) {
    return finishProductSampleRepository
        .countByMixDesignPlantCodeAndMixDesignRawMaterialId(plantCode, rawMaterialId);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesBySubCategoryId(Long subCategoryId) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialMaterialSubCategoryId(subCategoryId);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesBySubCategoryIdAndPlantCode(
      Long subCategoryId, String plantCode) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialMaterialSubCategoryIdAndMixDesignPlantCode(subCategoryId,
            plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesByCategoryId(Long materialCategoryId) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(materialCategoryId);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesByCategoryIdAndPlantCode(
      Long materialCategoryId, String plantCode) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryIdAndMixDesignPlantCode(
            materialCategoryId, plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesByRawMaterialId(Long rawMaterialId) {
    return finishProductSampleRepository.findByMixDesignRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesByRawMaterialIdAndPlantCode(
      Long rawMaterialId, String plantCode) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialIdAndMixDesignPlantCode(rawMaterialId, plantCode);
  }

  @Transactional(readOnly = true)
  public boolean checkAddValidation(
      List<FinishProductSampleRequestDto> finishProductSampleRequestDtoList) {
    for (FinishProductSampleRequestDto finishProductSampleRequestDto : finishProductSampleRequestDtoList) {
      if (finishProductSampleRequestDto.getMixDesignCode() == null
          || finishProductSampleRequestDto.getWorkOrderNumber() == null
          || finishProductSampleRequestDto.getMixDesignCode() == null
          || finishProductSampleRequestDto.getDate() == null) {
        return true;
      }
    }
    return false;
  }

}
