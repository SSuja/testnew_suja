package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.MaterialType;

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

  List<RawMaterial> findByNameContainsIgnoreCase(String name);

  boolean existsByPrefix(String prefix);

  List<RawMaterial> findByMaterialSubCategoryMaterialCategoryMainType(MainType mainType);

  boolean existsByPrefixAndMaterialSubCategoryIdAndPlantCode(String prefix,
      Long materialSubCategoryId, String plantCode);

  List<RawMaterial> findByMaterialType(MaterialType materialType);

  List<RawMaterial> findByPlantCode(String plantCode);

  List<RawMaterial> findBySubBusinessUnitId(Long id);

  Page<RawMaterial> findByPlantCodeOrPlantNullOrSubBusinessUnitId(String plantCode, Long subId,
      Pageable pageable);

  Long countByPlantCodeOrPlantNullOrSubBusinessUnitId(String plantCode, Long subId);

  List<RawMaterial> findByMaterialSubCategoryMaterialCategoryId(Long materialCategoryId);

  boolean existsByPrefixAndMaterialSubCategoryIdAndPlantCodeAndErpCode(String prefix,
      Long materialSubCategoryId, String plantCode, String erpCode);
}
