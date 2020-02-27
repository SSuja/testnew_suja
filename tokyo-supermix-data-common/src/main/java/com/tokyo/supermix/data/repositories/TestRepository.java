package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
  boolean existsByName(String name);
}
