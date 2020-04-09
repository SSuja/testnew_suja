package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MaterialTest;

public interface MaterialTestRepository extends JpaRepository<MaterialTest, String> {

  boolean existsByCode(String code);

  MaterialTest findByCode(String code);

  boolean existsByStatus(String status);

  List<MaterialTest> findByStatus(String status);

  boolean existsByTestConfigure(Long testConfigureId);

  List<MaterialTest> findByTestConfigure(Long testConfigureId);

  List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode);
}
