package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;

@Repository
public interface FinishProductSampleRepository extends JpaRepository<FinishProductSample, String>,
    QuerydslPredicateExecutor<FinishProductSample> {
  boolean existsByFinishProductCode(String code);

  boolean existsByMixDesignCode(String code);

  List<FinishProductSample> findByMixDesignCode(String mixDesignCode);

  boolean existsByEquipmentId(Long id);

  List<FinishProductSample> findByEquipmentId(Long id);

  List<FinishProductSample> findByMixDesignPlantCode(String plantCode);

  List<FinishProductSample> findByStatus(Status status);

  boolean existsByStatus(Status status);

  List<FinishProductSample> findByMixDesignPlantCodeIn(List<String> plantCodes);
  
  List<FinishProductSample> findByCodeContaining(String code);
}
