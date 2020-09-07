package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.security.UserPrincipal;

public interface FinishProductSampleIssueService {
  public List<FinishProductSampleIssue> getAllFinishProductSampleIssues();

  public void saveFinishProductSampleIssue(FinishProductSampleIssue finishProductSampleIssue);

  public void deleteFinishProductSampleIssue(String code);

  public FinishProductSampleIssue getFinishProductSampleIssueById(String code);

  public boolean isCodeExists(String code);

  public Page<FinishProductSampleIssue> searchFinishProductSampleIssue(Predicate predicate,
      int size, int page);

  public List<FinishProductSampleIssue> getFinishProductSampleIssueByPlantCode(String plantCode,
      Pageable pageable);

  public List<FinishProductSampleIssue> getAllFinishProductSampleIssueByPlant(
      UserPrincipal currentUser, Pageable pageable);

  public Long countFinishProductSampleIssue();

  public Long countFinishProductSampleIssueByPlant(String plantCode);

}
