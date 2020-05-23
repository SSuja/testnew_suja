package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestType;

public interface TestConfigureRepository
    extends JpaRepository<TestConfigure, Long>, QuerydslPredicateExecutor<TestConfigure> {
  List<TestConfigure> findByTestType(TestType testType);

  boolean existsByTestTypeIdAndTestId(Long testId, Long testTypeId);

  List<TestConfigure> findByTestTypeId(Long testTypeId);

  List<TestConfigure> findByTestTypeAndCoreTest(TestType testType, boolean coreTest);

  List<TestConfigure> findByCoreTest(boolean coreTest);
}
