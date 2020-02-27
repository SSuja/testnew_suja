package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.TestType;

public interface TestTypeRepository extends JpaRepository<TestType, Long> {
  boolean existsByType(String type);

}
