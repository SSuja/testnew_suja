package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.CoreTestConfigure;

public interface CoreTestConfigureRepository
    extends JpaRepository<CoreTestConfigure, Long>, QuerydslPredicateExecutor<CoreTestConfigure> {

  List<CoreTestConfigure> findByrawMaterialIdAndCoreTestTrue(Long rawMaterialId);

  List<CoreTestConfigure> findBytestConfigureId(Long testConfigureId);

  boolean existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrue(Long testConfigureId,
      Long rawMaterialId);

  List<CoreTestConfigure> findByrawMaterialIdAndApplicableTestTrue(Long rawMaterialId);

  List<CoreTestConfigure> findByTestConfigureIdAndMaterialCategoryIdAndApplicableTestTrue(
      Long testConfigureId, Long materialSubCategoryId);

  List<CoreTestConfigure> findByTestConfigureIdAndMaterialSubCategoryIdAndApplicableTestTrue(
      Long testConfigureId, Long materialSubCategoryId);

  List<CoreTestConfigure> findByTestConfigureIdAndRawMaterialIdAndApplicableTestTrue(
      Long testConfigureId, Long rawMaterialId);

  boolean existsBytestConfigureIdAndMaterialCategoryIdAndCoreTestTrueAndApplicableTestTrue(
      Long testConfigureId, Long materialCategoryId);

  boolean existsBytestConfigureIdAndMaterialSubCategoryIdAndCoreTestTrueAndApplicableTestTrue(
      Long testConfigureId, Long materialSubCategoryId);

  boolean existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrueAndApplicableTestTrue(
      Long testConfigureId, Long rawMaterialId);

  boolean existsBytestConfigureIdAndMaterialCategoryIdAndApplicableTestTrue(Long testConfigureId,
      Long materialCategoryId);

  List<CoreTestConfigure> findByrawMaterialIdAndCoreTestTrueAndApplicableTestTrue(
      Long rawMaterialId);


  boolean existsBytestConfigureIdAndRawMaterialIdAndApplicableTestTrue(Long testConfigureId,
      Long rawMaterialId);

  boolean existsBytestConfigureIdAndMaterialSubCategoryIdAndApplicableTestTrue(Long testConfigureId,
      Long materialSubCategoryId);

  List<CoreTestConfigure> findBytestConfigureIdAndMaterialSubCategoryId(Long testConfigureId,
      Long materialSubCategoryId);

  CoreTestConfigure findBytestConfigureIdAndRawMaterialId(Long testConfigureId, Long rawMaterialId);

  List<CoreTestConfigure> findByrawMaterialId(Long rawMaterialId);

  List<CoreTestConfigure> findByMaterialSubCategoryId(Long materialSubCategoryId);

  boolean existsByTestConfigureId(Long testConfigureId);

  Long deleteByTestConfigureId(Long testConfigureId);

  boolean existsByRawMaterialId(Long rawMaterialId);

  Long deleteByRawMaterialId(Long rawMaterialId);
}
