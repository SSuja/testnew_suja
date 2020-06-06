package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.Status;

public interface MaterialTestRepository
    extends JpaRepository<MaterialTest, String>, QuerydslPredicateExecutor<MaterialTest> {

  boolean existsByCode(String code);

  MaterialTest findByCode(String code);

  boolean existsByStatus(String status);

  List<MaterialTest> findByStatus(String status);

  boolean existsByTestConfigure(Long testConfigureId);

  List<MaterialTest> findByTestConfigure(Long testConfigureId);

  List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode);

  List<MaterialTest> findByIncomingSamplePlantCode(String plantCode);

  List<MaterialTest> findByCodeContaining(String code);

  List<MaterialTest> findByIncomingSampleCodeAndTestConfigureTestTypeClassification(
      String incomingSampleCode, String classification);

  List<MaterialTest> findByIncomingSampleCodeAndStatus(String incomingSampleCode, Status status);
}
