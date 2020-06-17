package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.TestConfigure;

public interface TestConfigureRepository
    extends JpaRepository<TestConfigure, Long>, QuerydslPredicateExecutor<TestConfigure> {

  //boolean existsByMaterialSubCategoryIdIdAndTestId(Long materialSubCategoryId,Long testId );

  List<TestConfigure> findByCoreTest(boolean coreTest);
}
