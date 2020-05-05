package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.FinishProductSample;

@Repository
public interface FinishProductSampleRepository extends JpaRepository<FinishProductSample, Long>,
    QuerydslPredicateExecutor<FinishProductSample> {
  boolean existsByFinishProductCode(Long code);

  boolean existsByMixDesignCode(String code);

  List<FinishProductSample> findByMixDesignCode(String mixDesignCode);

  boolean existsByConcreteMixerId(Long id);

  List<FinishProductSample> findByConcreteMixerId(Long id);
}
