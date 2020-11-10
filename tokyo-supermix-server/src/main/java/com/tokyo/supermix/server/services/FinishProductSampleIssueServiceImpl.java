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
import com.tokyo.supermix.data.dto.FinishProductSampleIssueResponseDto;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.entities.QFinishProductSampleIssue;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.FinishProductSampleIssueRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class FinishProductSampleIssueServiceImpl implements FinishProductSampleIssueService {

  @Autowired
  public FinishProductSampleIssueRepository finishProductSampleIssueRepository;
  @Autowired
  public FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<FinishProductSampleIssue> getAllFinishProductSampleIssues() {
    return finishProductSampleIssueRepository.findAll();
  }

  @Transactional
  public void saveFinishProductSampleIssue(FinishProductSampleIssue finishProductSampleIssue) {
    if (finishProductSampleIssue.getCode() == null) {
      String rawMaterialName = mixDesignRepository
          .getOne(finishProductSampleIssue.getMixDesign().getCode()).getRawMaterial().getName();
      String codePrefix = rawMaterialName + "-PO-";
      List<FinishProductSampleIssue> finishProductSampleIssueList =
          finishProductSampleIssueRepository.findByCodeContaining(codePrefix);
      if (finishProductSampleIssueList.size() == 0) {
        finishProductSampleIssue.setCode(codePrefix + String.format("%04d", 1));
      } else {
        finishProductSampleIssue.setCode(codePrefix
            + String.format("%04d", maxNumberFromCode(finishProductSampleIssueList) + 1));
      }
    }
    FinishProductSampleIssue finishProductSampleIssueObj =
        finishProductSampleIssueRepository.save(finishProductSampleIssue);
    if (finishProductSampleIssueObj != null) {
      emailNotification.sendFinishProductSampleIssueEmail(finishProductSampleIssueObj);
    }
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductSampleIssue> finishProductSampleIssueList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductSampleIssueList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductSampleIssue(String code) {
    finishProductSampleIssueRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public FinishProductSampleIssue getFinishProductSampleIssueById(String code) {
    return finishProductSampleIssueRepository.findById(code).get();
  }

  @Transactional(readOnly = true)
  public boolean isCodeExists(String code) {
    return finishProductSampleIssueRepository.existsById(code);
  }

  @Transactional(readOnly = true)
  public Page<FinishProductSampleIssue> searchFinishProductSampleIssue(Predicate predicate,
      int size, int page) {
    return finishProductSampleIssueRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getFinishProductSampleIssueByPlantCode(String plantCode,
      Pageable pageable) {
    return finishProductSampleRepository
        .findByWorkOrderNumberNullAndMixDesignPlantCodeOrderByUpdatedAtDesc(plantCode, pageable)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<FinishProductSample> getAllFinishProductSampleIssueByPlant(UserPrincipal currentUser,
      Pageable pageable) {
    return finishProductSampleRepository
        .findByWorkOrderNumberNotNullAndMixDesignPlantCodeInOrderByUpdatedAtDesc(
            currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
                PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE),
            pageable)
        .toList();
  }

  @Transactional(readOnly = true)
  public Long countFinishProductSampleIssue() {
    return finishProductSampleRepository.countByWorkOrderNumberNotNull();
  }

  @Transactional(readOnly = true)
  public Long countFinishProductSampleIssueByPlant(String plantCode) {
    return finishProductSampleRepository
        .countByMixDesignPlantCodeAndWorkOrderNumberNotNull(plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductSampleIssueResponseDto> searchFinishProductSampleIssue(
      BooleanBuilder booleanBuilder, String workOrderNumber, String materialName,
      String mixDesignCode, String pourName, String projectName, String customerName,
      String plantCode, Pageable pageable, Pagination pagination) {

    if (workOrderNumber != null && !workOrderNumber.isEmpty()) {
      booleanBuilder.and(QFinishProductSampleIssue.finishProductSampleIssue.workOrderNumber
          .startsWithIgnoreCase(workOrderNumber));
    }
    if (materialName != null && !materialName.isEmpty()) {
      booleanBuilder
          .and(QFinishProductSampleIssue.finishProductSampleIssue.mixDesign.rawMaterial.name
              .startsWithIgnoreCase(materialName));
    }
    if (mixDesignCode != null && !mixDesignCode.isEmpty()) {
      booleanBuilder.and(QFinishProductSampleIssue.finishProductSampleIssue.mixDesign.code
          .startsWithIgnoreCase(mixDesignCode));
    }

    if (pourName != null && !pourName.isEmpty()) {
      booleanBuilder.and(QFinishProductSampleIssue.finishProductSampleIssue.pour.name
          .startsWithIgnoreCase(pourName));
    }
    if (projectName != null && !projectName.isEmpty()) {
      booleanBuilder.and(QFinishProductSampleIssue.finishProductSampleIssue.project.name
          .startsWithIgnoreCase(projectName));
    }
    if (customerName != null && !customerName.isEmpty()) {
      booleanBuilder.and(QFinishProductSampleIssue.finishProductSampleIssue.project.customer.name
          .startsWithIgnoreCase(customerName));
    }

    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QFinishProductSampleIssue.finishProductSampleIssue.mixDesign.plant.code
          .startsWithIgnoreCase(plantCode));
    }
    pagination
        .setTotalRecords(((Collection<FinishProductSampleIssue>) finishProductSampleIssueRepository
            .findAll(booleanBuilder)).stream().count());
    return mapper.map(finishProductSampleIssueRepository.findAll(booleanBuilder, pageable).toList(),
        FinishProductSampleIssueResponseDto.class);
  }
}
