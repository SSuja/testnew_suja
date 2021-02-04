package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.MainType;

public interface TestConfigureRepository
    extends JpaRepository<TestConfigure, Long>, QuerydslPredicateExecutor<TestConfigure> {
  List<TestConfigure> findByCoreTest(boolean coreTest);

  boolean existsByTestIdAndMaterialSubCategoryIdAndRawMaterialId(Long testId,
      Long materialSubCategoryId, Long rawMaterialId);

  List<TestConfigure> findByTestType(MainType testType);

  boolean existsByTestType(MainType testType);

  List<TestConfigure> findByMaterialSubCategoryId(Long materialSubCategoryId);

  List<TestConfigure> findByMaterialSubCategoryIdAndCoreTestTrue(Long materialSubCategoryId);

  List<TestConfigure> findByMaterialSubCategoryIdAndTestType(Long materialSubCategoryId,
      MainType testType);

  TestConfigure findByMaterialCategoryName(String materialCategoryName);

  List<TestConfigure> findByRawMaterialIdAndCoreTestTrue(Long rawMaterialId);

  boolean existsByRawMaterialId(Long rawMaterialId);

  List<TestConfigure> findByMaterialCategoryId(Long materialCategoryId);

  boolean existsByPrefix(String prefix);

  List<TestConfigure> findByMaterialCategoryIdAndCoreTestTrue(Long materialCategoryId);

  List<TestConfigure> findByRawMaterialId(Long rawNaterialId);

  List<TestConfigure> findByMaterialCategoryIdOrMaterialSubCategoryIdOrRawMaterialId(
      Long materialCategoryId, Long materialSubCategoryId, Long rawMaterialId);

  TestConfigure findByTestId(Long TestId);

  List<TestConfigure> findByMaterialCategoryIdAndMaterialSubCategoryNull(Long materialCategoryId);

  List<TestConfigure> findByMaterialSubCategoryIdAndRawMaterialNull(Long materialSubCategoryId);

  List<TestConfigure> findByTestTypeAndDueDayNotNull(MainType testType);

  List<TestConfigure> findAllByOrderByIdDesc(Pageable pageable);

  boolean existsByTestIdAndMaterialCategoryIdAndMaterialSubCategoryIdAndRawMaterialId(Long testId,
      Long materialCategoryId, Long materialSubCategoryId, Long rawMaterialId);

  boolean existsByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId);

  boolean existsByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId);

  boolean existsByTestIdAndRawMaterialId(Long testId, Long rawMaterialId);

  boolean existsByMaterialSubCategoryId(Long materialSubCategoryId);

  boolean existsByMaterialCategoryId(Long materialCategoryId);
}
