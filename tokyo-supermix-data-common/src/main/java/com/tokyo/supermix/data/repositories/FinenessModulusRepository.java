package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.FinenessModulus;

public interface FinenessModulusRepository
    extends JpaRepository<FinenessModulus, Long>, QuerydslPredicateExecutor<FinenessModulus> {
  List<FinenessModulus> findByMaterialSubCategoryId(Long materialSubCategoryId);

  boolean existsByMaterialSubCategoryId(Long materialSubCategoryId);
}
