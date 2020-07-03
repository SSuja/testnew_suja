package com.tokyo.supermix.data.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.SieveSize;

public interface SieveSizeRepository
    extends JpaRepository<SieveSize, Long>, QuerydslPredicateExecutor<SieveSize> {
  List<SieveSize> findByMaterialSubCategoryId(Long materialSubCategoryId);

  List<SieveSize> findByMaterialSubCategoryIdOrderBySizeDesc(Long materialSubCategoryId);

  boolean existsBySizeAndMaterialSubCategoryId(Double size, Long materialSubCategoryId);

  SieveSize findBySizeAndMaterialSubCategoryId(Double size, Long materialSubCategoryId);
}
