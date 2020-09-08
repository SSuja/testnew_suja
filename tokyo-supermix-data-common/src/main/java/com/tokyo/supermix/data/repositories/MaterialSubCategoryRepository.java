package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;

public interface MaterialSubCategoryRepository extends JpaRepository<MaterialSubCategory, Long>,
    QuerydslPredicateExecutor<MaterialSubCategory> {
  boolean existsByName(String name);

  List<MaterialSubCategory> findByMaterialCategory(MaterialCategory materialCategory);

  public MaterialSubCategory findByName(String name);

  boolean existsByMaterialCategoryId(Long materialCategoryId);

  List<MaterialSubCategory> findByMaterialCategoryId(Long materialCategoryId);

  boolean existsByNameAndMaterialCategoryId(String name, Long materialCategoryId);

  boolean existsByPrefix(String prifix);
}
