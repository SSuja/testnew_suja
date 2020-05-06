package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;

public interface FinishProductSampleIssueService {
  public List<FinishProductSampleIssue> getAllFinishProductSampleIssues();

  public void saveFinishProductSampleIssue(FinishProductSampleIssue finishProductSampleIssue);

  public void deleteFinishProductSampleIssue(Long id);

  public FinishProductSampleIssue getFinishProductSampleIssueById(Long id);

  public boolean isIdExists(Long id);

  public Page<FinishProductSampleIssue> searchFinishProductSampleIssue(Predicate predicate,
      int size, int page);
}
