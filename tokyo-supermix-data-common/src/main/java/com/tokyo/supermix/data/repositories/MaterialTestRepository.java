package com.tokyo.supermix.data.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.Status;

public interface MaterialTestRepository extends JpaRepository<MaterialTest, String>,
    QuerydslPredicateExecutor<MaterialTest>, PagingAndSortingRepository<MaterialTest, String> {

  boolean existsByCode(String code);

  MaterialTest findByCode(String code);

  MaterialTest findByCodeAndIncomingSamplePlantCode(String materialTestCode, String plantCode);

  boolean existsByStatus(String status);

  List<MaterialTest> findByStatus(String status);

  boolean existsByTestConfigureId(Long testConfigureId);

  List<MaterialTest> findByTestConfigureIdAndIncomingSamplePlantCode(Long testConfigureId,
      String plantCode);

  List<MaterialTest> findByTestConfigureId(Long testConfigureId);

  List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode);

  List<MaterialTest> findByIncomingSamplePlantCode(String plantCode);

  List<MaterialTest> findByCodeContaining(String code);

  List<MaterialTest> findByIncomingSampleCodeAndStatus(String incomingSampleCode, Status status);

  List<MaterialTest> findByIncomingSampleCodeAndTestConfigureReportFormatAndIncomingSamplePlantCode(
      String incomingSampleCode, ReportFormat reportFormat, String plantCode);

  List<MaterialTest> findByIncomingSampleCodeAndTestConfigureReportFormat(String incomingSampleCode,
      ReportFormat reportFormat);

  boolean existsByIncomingSampleCodeAndStatusAndTestConfigureTestName(String incomingSampleCode,
      Status status, String testName);

  Integer countByIncomingSampleCodeAndStatusAndTestConfigureTestName(String incomingSampleCode,
      Status status, String testName);

  List<MaterialTest> findByTestConfigureTestType(MainType testType);

  List<MaterialTest> findByIncomingSamplePlantCodeIn(List<String> plantCodes);

  List<MaterialTest> findByIncomingSampleCodeContaining(String incomingSampleCode);

  boolean existsByIncomingSamplePlantCode(String plantCode);

  List<MaterialTest> findByIncomingSampleCodeAndIncomingSamplePlantCode(String incomingSampleCode,
      String plantCode);

  List<MaterialTest> findByIncomingSampleCodeAndTestConfigureId(String incomingSampleCode,
      Long testConfigId);

  List<MaterialTest> findByIncomingSampleCodeAndTestConfigureIdAndIncomingSamplePlantCode(
      String incomingSampleCode, Long testConfigId, String plantCode);

  List<MaterialTest> findByTestConfigureIdAndIncomingSampleRawMaterialId(Long testConfigureId,
      Long rawMaterialId);

  List<MaterialTest> findAllByIncomingSamplePlantCode(String plantCode, Pageable pageable);

  Page<MaterialTest> findAll(Pageable pageable);

  Long countByIncomingSamplePlantCode(String plantCode);

  boolean existsByIncomingSampleCodeAndTestConfigureId(String incomingSampleCode,
      Long testConfigId);
  Set<MaterialTest> findByTestConfigureDueDayNotNull();
}
