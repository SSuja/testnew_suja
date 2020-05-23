package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;

public interface FinishProductSampleIssueRepository
    extends JpaRepository<FinishProductSampleIssue, Long>,
    QuerydslPredicateExecutor<FinishProductSampleIssue> {
  List<FinishProductSampleIssue> findByFinishProductSampleMixDesignPlantCode(String plantCode);

}
