package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;

@Repository
public interface MaterialQualityParameterRepository
    extends JpaRepository<MaterialQualityParameter, Long>,
    QuerydslPredicateExecutor<MaterialQualityParameter> {

  boolean existsByParameterIdAndRawMaterialId(Long parameterId, Long rawMaterialId);

  boolean existsByParameterIdAndMaterialSubCategoryId(Long parameterId, Long subCategoryId);
}
