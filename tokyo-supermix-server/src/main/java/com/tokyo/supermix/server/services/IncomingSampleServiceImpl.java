package com.tokyo.supermix.server.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.QIncomingSample;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class IncomingSampleServiceImpl implements IncomingSampleService {
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  RawMaterialRepository rawMaterialRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional
  public void createIncomingSample(IncomingSample incomingSample) {
    if (incomingSample.getCode() == null) {
      String subCatPrefix = rawMaterialRepository.getOne(incomingSample.getRawMaterial().getId())
          .getMaterialSubCategory().getPrefix();
      String codePrefix = incomingSample.getPlant().getCode() + "-" + subCatPrefix + "-INC-";
      List<IncomingSample> incomingSampleList =
          incomingSampleRepository.findByCodeContaining(codePrefix);
      if (incomingSampleList.size() == 0) {
        incomingSample.setCode(codePrefix + String.format("%04d", 1));
      } else {
        incomingSample
            .setCode(codePrefix + String.format("%04d", maxNumberFromCode(incomingSampleList) + 1));
      }
    }
    incomingSample.setStatus(Status.NEW);
    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
    incomingSample.setDate(date);
    IncomingSample incomingSampleObj = incomingSampleRepository.save(incomingSample);
    if (incomingSampleObj != null) {
      emailNotification.sendIncomingSampleEmail(incomingSampleObj);
    }
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<IncomingSample> incomingSampleList) {
    List<Integer> list = new ArrayList<Integer>();
    incomingSampleList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.lastIndexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional
  public void updateIncomingSample(IncomingSample incomingSample) {
    incomingSampleRepository.save(incomingSample);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteIncomingSample(String code) {
    incomingSampleRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public IncomingSample getIncomingSampleById(String code) {
    return incomingSampleRepository.findById(code).get();
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getAllIncomingSamples() {
    return incomingSampleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isIncomingSampleExist(String code) {
    return incomingSampleRepository.existsByCode(code);
  }

  @Transactional
  public void updateStatusForIncomingSample(String code, Status status) {
    IncomingSample incomingSample = incomingSampleRepository.findById(code).get();
    incomingSample.setStatus(status);
    incomingSampleRepository.save(incomingSample);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getIncomingSampleByStatus(Status status) {
    return incomingSampleRepository.findIncomingSampleByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isIncomingSampleStatusExist(Status status) {
    return incomingSampleRepository.existsByStatus(status);
  }

  // @Transactional(readOnly = true)
  // public Page<IncomingSample> searchIncomingSample(Predicate predicate, int page, int size) {
  // return incomingSampleRepository.findAll(predicate,
  // PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  // }

  @Transactional(readOnly = true)
  public List<IncomingSample> getIncomingSampleByPlantCode(String plantCode, Pageable pageable) {
    return incomingSampleRepository.findByPlantCodeOrderByUpdatedAtDesc(plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getAllIncomingSamplesByCurrentUser(UserPrincipal currentUser,
      Pageable pageable) {
    return incomingSampleRepository.findAllByPlantCodeInOrderByUpdatedAtDesc(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_INCOMING_SAMPLE),
        pageable);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getByMaterialSubCategoryPlantWise(Long materialSubCategoryId,
      String plantCode, String code) {
    if (code.isEmpty()) {
      return null;
    }
    return incomingSampleRepository
        .findByRawMaterialMaterialSubCategoryIdAndPlantCodeAndCodeStartsWith(materialSubCategoryId,
            plantCode, code);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getByMaterialSubCategory(Long materialSubCategoryId, String code) {
    return incomingSampleRepository
        .findByRawMaterialMaterialSubCategoryIdAndCodeStartsWith(materialSubCategoryId, code);
  }

  @Transactional(readOnly = true)
  public Long getCountIncomingSample() {
    return incomingSampleRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountIncomingSampleByPlantCode(String plantCode) {
    return incomingSampleRepository.countByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getIncomingSampleCodeByPlantCode(String plantCode, String code) {
    if (code.isEmpty()) {
      return null;
    }
    return incomingSampleRepository.findByPlantCodeAndCodeStartsWith(plantCode, code);
  }


  @Transactional(readOnly = true)
  public List<IncomingSample> getIncomingSampleCode(String code) {
    if (code.isEmpty()) {
      return null;
    }
    return incomingSampleRepository.findByCodeStartsWith(code);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> searchIncomingSample(String code, String vehicleNo, Date date,
      String status, String rawMaterialName, String plantName, String supplierName,
      BooleanBuilder booleanBuilder, Pageable pageable, RawMaterialSampleType rawMaterialSampleType,
      String plantCode, Pagination pagination) {
    if (code != null && !code.isEmpty()) {
      booleanBuilder.and(QIncomingSample.incomingSample.code.startsWithIgnoreCase(code));
    }
    if (vehicleNo != null && !vehicleNo.isEmpty()) {
      booleanBuilder.and(QIncomingSample.incomingSample.vehicleNo.startsWithIgnoreCase(vehicleNo));
    }
    if (date != null) {
      booleanBuilder.and(QIncomingSample.incomingSample.date.eq(date));
    }
    if (rawMaterialName != null && !rawMaterialName.isEmpty()) {
      booleanBuilder.and(
          QIncomingSample.incomingSample.rawMaterial.name.startsWithIgnoreCase(rawMaterialName));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QIncomingSample.incomingSample.plant.name.startsWithIgnoreCase(plantName));
    }
    if (supplierName != null && !supplierName.isEmpty()) {
      booleanBuilder
          .and(QIncomingSample.incomingSample.supplier.name.startsWithIgnoreCase(supplierName));
    }
    if (status != null) {
      booleanBuilder.and(QIncomingSample.incomingSample.status.stringValue().startsWith(status));
    }
    if (!plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      booleanBuilder.and(QIncomingSample.incomingSample.plant.code.startsWithIgnoreCase(plantCode));
    }
    pagination.setTotalRecords(
        incomingSampleRepository.countByRawMaterialSampleType(rawMaterialSampleType));
    return incomingSampleRepository.findAll(booleanBuilder, pageable).toList().stream()
        .filter(sample -> sample.getRawMaterialSampleType().equals(rawMaterialSampleType))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getByMaterialSubCategory(Long materialSubCategoryId) {
    return incomingSampleRepository.findByRawMaterialMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getByMaterialSubCategoryPlantWise(Long materialSubCategoryId,
      String plantCode) {
    return incomingSampleRepository
        .findByRawMaterialMaterialSubCategoryIdAndPlantCode(materialSubCategoryId, plantCode);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> getBySupplierId(Long supplierId) {
    return incomingSampleRepository.findBySupplierId(supplierId);
  }

  @Transactional(readOnly = true)
  public boolean isSampleExistsByRawMaterialSample(RawMaterialSampleType rawMaterialSampleType) {
    return incomingSampleRepository.existsByRawMaterialSampleType(rawMaterialSampleType);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> findByRawMaterialSampleType(
      RawMaterialSampleType rawMaterialSampleType, Pageable pageable) {
    return incomingSampleRepository.findAllByRawMaterialSampleType(rawMaterialSampleType, pageable);
  }

  @Transactional(readOnly = true)
  public Long countAllSampleByRawMaterialSampleType(RawMaterialSampleType rawMaterialSampleType) {
    return incomingSampleRepository.countByRawMaterialSampleType(rawMaterialSampleType);
  }

  @Override
  public Long countByRawMaterialSampleTypeAndPlantCode(RawMaterialSampleType rawMaterialSampleType,
      String plantCode) {
    return incomingSampleRepository.countByRawMaterialSampleTypeAndPlantCode(rawMaterialSampleType,
        plantCode);
  }

  @Override
  public List<IncomingSample> findByRawMaterialSampleTypeAndPlantCode(
      RawMaterialSampleType rawMaterialSampleType, String plantCode, Pageable pageable) {
    return incomingSampleRepository
        .findAllByRawMaterialSampleTypeAndPlantCode(rawMaterialSampleType, plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public List<IncomingSample> findAllByPlantCodeAndRawMaterialSampleTypeInOrderByUpdatedAtDesc(
      UserPrincipal currentUser, RawMaterialSampleType rawMaterialSampleType, Pageable pageable) {
    return incomingSampleRepository.findByPlantCodeInAndRawMaterialSampleTypeOrderByUpdatedAtDesc(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_INCOMING_SAMPLE),
        rawMaterialSampleType, pageable);
  }

  // @Transactional(readOnly = true)
  // public List<IncomingSample> findAllByRawMaterialSampleType(
  // RawMaterialSampleType rawMaterialSampleType) {
  // return incomingSampleRepository.findAllByRawMaterialSampleType(rawMaterialSampleType);
  // }
}
