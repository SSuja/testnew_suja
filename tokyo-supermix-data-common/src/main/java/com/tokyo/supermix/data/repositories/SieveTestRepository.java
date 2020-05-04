package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.SieveTest;

public interface SieveTestRepository
    extends JpaRepository<SieveTest, String>, QuerydslPredicateExecutor<SieveTest> {
  SieveTest findByCode(String code);

  boolean existsByCode(String code);

  SieveTest findByIncomingSampleCode(String incomingSampleCode);
}
