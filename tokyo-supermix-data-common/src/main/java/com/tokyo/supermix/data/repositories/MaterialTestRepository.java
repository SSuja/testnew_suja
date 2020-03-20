package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MaterialTest;

public interface MaterialTestRepository extends JpaRepository<MaterialTest, String> {

  boolean existsByCode(String code);

  MaterialTest findByCode(String code);

  boolean existsByStatus(String status);

  List<MaterialTest> findByStatus(String status);

  boolean existsByTest(Long testId);

  List<MaterialTest> findByTest(Long testId);

  List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode);
}
