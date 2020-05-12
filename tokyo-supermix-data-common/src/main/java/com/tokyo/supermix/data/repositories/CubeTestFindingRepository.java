package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.CubeTestFinding;

public interface CubeTestFindingRepository
    extends JpaRepository<CubeTestFinding, Long>, QuerydslPredicateExecutor<CubeTestFinding> {
  List<CubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId);

  boolean existsByFinishProductSampleId(Long finishProductSampleId);
}
