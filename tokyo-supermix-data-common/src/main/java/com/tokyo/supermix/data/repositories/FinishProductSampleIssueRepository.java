package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;

public interface FinishProductSampleIssueRepository
    extends JpaRepository<FinishProductSampleIssue, String>,
    QuerydslPredicateExecutor<FinishProductSampleIssue> {
  FinishProductSampleIssue findByFinishProductSampleCode(String finishProductSampleCode);

  List<FinishProductSampleIssue> findByFinishProductSampleMixDesignPlantCode(String plantCode);

  List<FinishProductSampleIssue> findByProjectPlantCodeIn(List<String> plantCodes);

  List<FinishProductSampleIssue> findByCodeContaining(String code);
}
