package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.RawMaterial;

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

  Long countByPlantCode(String plantCode);
  
  List<RawMaterial> findByPlantCodeAndNameStartsWith(String plantCode, String name);

  List<RawMaterial> findByNameStartsWith(String name);
}
