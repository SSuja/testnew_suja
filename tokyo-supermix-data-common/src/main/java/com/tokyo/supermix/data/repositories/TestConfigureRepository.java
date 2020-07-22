package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.TestType;

public interface TestConfigureRepository
    extends JpaRepository<TestConfigure, Long>, QuerydslPredicateExecutor<TestConfigure> {
  List<TestConfigure> findByCoreTest(boolean coreTest);

  boolean existsByTestIdAndMaterialCategoryIdAndMaterialSubCategoryId(Long testId,
      Long materialCategoryId, Long materialSubCategoryId);

  List<TestConfigure> findByTestType(TestType testType);

  boolean existsByTestType(TestType testType);

  List<TestConfigure> findByMaterialSubCategoryId(Long materialSubCategoryId);

  List<TestConfigure> findByMaterialSubCategoryIdAndCoreTestTrue(Long materialSubCategoryId);

  List<TestConfigure> findByMaterialSubCategoryIdAndTestType(Long materialSubCategoryId,
      TestType testType);

}
