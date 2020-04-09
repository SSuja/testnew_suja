package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestType;

public interface TestConfigureRepository extends JpaRepository<TestConfigure, Long> {
  List<TestConfigure> findByTestType(TestType testType);

  boolean existsByTestIdAndTestTypeId(Long testId, Long testTypeId);

  List<TestConfigure> findByTestTypeId(Long testTypeId);
}
