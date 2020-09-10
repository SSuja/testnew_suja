package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
      String codePrefix = rawMaterialName + "-PP-";
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
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
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
  public List<FinishProductSample> getFinishProductSampleByEquipmentId(Long id) {
    return finishProductSampleRepository.findByEquipmentId(id);
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
        .findByMixDesignPlantCodeOrderByUpdatedAtDesc(plantCode, pageable).toList();
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
    return finishProductSampleRepository.findByMixDesignPlantCodeInOrderByUpdatedAtDesc(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE),
        pageable).toList();
  }

  @Transactional(readOnly = true)
  public Long getCountFinishProductSample() {
    return finishProductSampleRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountFinishProductSampleByPlantCode(String plantCode) {
    return finishProductSampleRepository.countByMixDesignPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSampleResponseDto> searchFinishProductSample(
      BooleanBuilder booleanBuilder, String finishProductCode, String equipmentName,
      String mixDesignCode, String plantName, String plantCode, Pageable pageable,
      Pagination pagination) {

    if (finishProductCode != null && !finishProductCode.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.finishProductCode
          .startsWithIgnoreCase(finishProductCode));
    }
    if (equipmentName != null && !equipmentName.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.equipment.name
          .startsWithIgnoreCase(equipmentName));
    }
    if (mixDesignCode != null && !mixDesignCode.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.mixDesign.code
          .startsWithIgnoreCase(mixDesignCode));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.mixDesign.plant.name
          .startsWithIgnoreCase(plantName));
    }

    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QFinishProductSample.finishProductSample.mixDesign.plant.code
          .startsWithIgnoreCase(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<FinishProductSample>) finishProductSampleRepository.findAll(booleanBuilder))
            .stream().count());
    return mapper.map(finishProductSampleRepository.findAll(booleanBuilder, pageable).toList(),
        FinishProductSampleResponseDto.class);
  }

  public List<FinishProductSample> getFinishProductSamplesBySubCategoryId(Long subCategoryId,
      Pageable pageable) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialMaterialSubCategoryId(subCategoryId, pageable);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesBySubCategoryIdAndPlantCode(
      Long subCategoryId, String plantCode, Pageable pageable) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialMaterialSubCategoryIdAndMixDesignPlantCode(subCategoryId,
            plantCode, pageable);
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
  public List<FinishProductSample> getFinishProductSamplesByCategoryId(Long materialCategoryId,
      Pageable pageable) {
    return finishProductSampleRepository
        .findByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(materialCategoryId, pageable);
  }

  @Transactional(readOnly = true)
  public Long getCountCategoryFinishProductSampleByPlantCode(String plantCode,
      Long materialCategoryId) {
    return finishProductSampleRepository.countByMixDesignPlantCodeAndMixDesignRawMaterialMaterialSubCategoryMaterialCategoryId(plantCode, materialCategoryId);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSamplesByCategoryIdAndPlantCode(
      Long materialCategoryId, String plantCode, Pageable pageable) {
    return finishProductSampleRepository.findByMixDesignRawMaterialMaterialSubCategoryMaterialCategoryIdAndMixDesignPlantCode(materialCategoryId, plantCode, pageable);
  }
}
