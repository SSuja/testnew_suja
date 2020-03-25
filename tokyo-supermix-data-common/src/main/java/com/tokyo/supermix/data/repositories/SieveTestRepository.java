package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.SieveTest;

public interface SieveTestRepository extends JpaRepository<SieveTest, String> {
  SieveTest findByCode(String code);

  boolean existsByCode(String code);
}
