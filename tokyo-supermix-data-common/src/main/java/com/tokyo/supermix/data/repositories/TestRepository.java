package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.entities.TestType;

public interface TestRepository extends JpaRepository<Test, Long> {
  boolean existsByName(String name);

  List<Test> findByTestType(TestType testType);

  boolean existsByNameAndTestTypeId(String name, Long testTypeId);

  List<Test> findByTestTypeId(Long testTypeId);
}
