package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;

public interface MaterialAcceptedValueRepository extends JpaRepository<MaterialAcceptedValue, Long>,
    QuerydslPredicateExecutor<MaterialAcceptedValue> {
  List<MaterialAcceptedValue> findByTestConfigure(TestConfigure testConfigure);

  public boolean existsByTestConfigureIdAndRawMaterialId(Long testConfigureId, Long rawMaterialId);

  List<MaterialAcceptedValue> findByTestConfigureId(Long testConfigureId);

  boolean existsByRawMaterialId(Long rawMaterialId);

  List<MaterialAcceptedValue> findByTestConfigureIdAndRawMaterialId(Long testConfigureId,
      Long rawMaterialId);

  List<MaterialAcceptedValue> findByTestConfigureIdAndTestConfigureRawMaterialId(
      Long testConfigureId, Long rawMaterialId);

  MaterialAcceptedValue findByTestConfigureIdAndTestEquationId(Long testConfigureId,
      Long testEquationId);

  MaterialAcceptedValue findByTestConfigureIdAndRawMaterialIdAndTestEquationId(Long testConfigureId,
      Long rawMaterialId, Long testEquationId);

  public boolean existsByTestConfigureIdAndRawMaterialIdAndTestParameterId(Long testConfigureId,
      Long rawMaterialId, Long testParameterId);

  public boolean existsByTestConfigureId(Long testConfigureId);

  MaterialAcceptedValue findByTestConfigureIdAndTestParameterIdAndRawMaterialId(
      Long testConfigureId, Long testParameterId, Long rawMaterialId);

  Long countByTestConfigureIdAndAndRawMaterialIdAndFinalResultTrue(Long testConfigureId,
      Long rawMaterialId);

  List<MaterialAcceptedValue> findByTestConfigureIdAndFinalResultTrue(Long testConfigureId);

  MaterialAcceptedValue findByTestConfigureIdAndRawMaterialIdAndTestParameterIdAndFinalResultTrue(
      Long testConfigureId, Long rawMaterialId, Long testParameterId);

  List<MaterialAcceptedValue> findByTestConfigureIdAndRawMaterialIdAndFinalResultTrue(
      Long testConfigureId, Long rawMaterialId);

  MaterialAcceptedValue findByTestConfigureIdAndMaterialSubCategoryIdAndTestParameterIdAndFinalResultTrue(
      Long testConfigureId, Long materialSubCategoryId, Long testParameterId);

  List<MaterialAcceptedValue> findByTestConfigureIdAndMaterialSubCategoryIdAndFinalResultTrue(
      Long testConfigureId, Long materialSubCategoryId);

  List<MaterialAcceptedValue> findByTestConfigureIdAndMaterialSubCategoryId(Long testConfigureId,
      Long materialSubCategoryId);

  MaterialAcceptedValue findByTestConfigureIdAndTestParameterIdAndMaterialSubCategoryId(
      Long testConfigureId, Long testParameterId, Long MaterialSubCategoryId);

  Long countByTestConfigureIdAndCategoryAcceptedType(Long testConfigureId,
      CategoryAcceptedType categoryAcceptedType);

  Page<MaterialAcceptedValue> findByTestConfigureIdAndCategoryAcceptedType(Pageable pageable,
      Long testConfigureId, CategoryAcceptedType categoryAcceptedType);

  public boolean existsByTestConfigureIdAndMaterialSubCategoryIdAndTestParameterId(
      Long testConfigureId, Long MaterialSubCategoryId, Long testParameterId);

  public boolean existsByMaterialSubCategoryId(Long materialSubCategoryId);

  boolean existsByRawMaterialMaterialSubCategoryId(Long materialSubCategoryId);

  Long deleteByTestConfigureId(Long testConfigureId);
  // ByTitle

  public boolean existsByTestConfigureIdAndMaterialSubCategoryId(Long testConfigureId,
      Long materialSubCategoryId);
}
