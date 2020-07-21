package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.repositories.FinishProductSampleIssueRepository;
import com.tokyo.supermix.data.repositories.ProjectRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class FinishProductSampleIssueServiceImpl implements FinishProductSampleIssueService {

  @Autowired
  public FinishProductSampleIssueRepository finishProductSampleIssueRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional(readOnly = true)
  public List<FinishProductSampleIssue> getAllFinishProductSampleIssues() {
    return finishProductSampleIssueRepository.findAll();
  }

  @Transactional
  public void saveFinishProductSampleIssue(FinishProductSampleIssue finishProductSampleIssue) {

    if (finishProductSampleIssue.getCode() == null) {
      String plantPrefix = projectRepository.getOne(finishProductSampleIssue.getProject().getCode())
          .getPlant().getCode();
      String codePrefix = plantPrefix + "-PO-";
      List<FinishProductSampleIssue> finishProductSampleIssueList =
          finishProductSampleIssueRepository.findByCodeContaining(codePrefix);
      if (finishProductSampleIssueList.size() == 0) {
        finishProductSampleIssue.setCode(codePrefix + String.format("%04d", 1));
      } else {
        finishProductSampleIssue.setCode(codePrefix
            + String.format("%04d", maxNumberFromCode(finishProductSampleIssueList) + 1));
      }
    }
    finishProductSampleIssueRepository.save(finishProductSampleIssue);
    emailNotification.sendFinishProductSampleIssueEmail(finishProductSampleIssue);
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
  public List<FinishProductSampleIssue> getFinishProductSampleIssueByPlantCode(String plantCode) {
    return finishProductSampleIssueRepository
        .findByFinishProductSampleMixDesignPlantCode(plantCode);
  }

  @Override
  public List<FinishProductSampleIssue> getAllFinishProductSampleIssueByPlant(
      UserPrincipal currentUser) {
    return finishProductSampleIssueRepository.findByProjectPlantCodeIn(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE_ISSUE));
  }
}
