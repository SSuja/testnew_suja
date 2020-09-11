package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.enums.MainType;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long>,
    QuerydslPredicateExecutor<RawMaterial>, PagingAndSortingRepository<RawMaterial, Long> {
  boolean existsByName(String name);

  List<RawMaterial> findByMaterialSubCategoryId(Long materialSubCategoryId);

  RawMaterial findByName(String name);

  List<RawMaterial> findByActiveTrue();

  Page<RawMaterial> findByPlantCodeOrPlantNull(String plantCode, Pageable pageable);

  List<RawMaterial> findByMaterialSubCategoryIdAndPlantCodeOrPlantNull(Long materialSubCategoryId,
      String plantCode);

  Page<RawMaterial> findAll(Pageable pageable);

  Long countByPlantCodeOrPlantNull(String plantCode);

  List<RawMaterial> findByPlantCodeOrPlantNullAndNameStartsWith(String plantCode, String name);

  List<RawMaterial> findByNameStartsWith(String name);

  boolean existsByPrefix(String prefix);

  List<RawMaterial> findByMaterialSubCategoryMaterialCategoryMainType(MainType mainType);
}
