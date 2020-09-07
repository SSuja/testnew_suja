package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;

public interface FinishProductSampleIssueRepository
    extends JpaRepository<FinishProductSampleIssue, String>,
    QuerydslPredicateExecutor<FinishProductSampleIssue>,
    PagingAndSortingRepository<FinishProductSampleIssue, String> {
  Page<FinishProductSampleIssue> findByMixDesignPlantCode(String plantCode, Pageable pageable);

  Page<FinishProductSampleIssue> findByProjectPlantCodeIn(List<String> plantCodes,
      Pageable pageable);

  List<FinishProductSampleIssue> findByCodeContaining(String code);

  Long countByMixDesignPlantCode(String plantCode);
}
