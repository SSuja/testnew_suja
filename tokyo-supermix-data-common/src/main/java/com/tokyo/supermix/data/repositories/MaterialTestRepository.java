package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.Status;

public interface MaterialTestRepository
    extends JpaRepository<MaterialTest, String>, QuerydslPredicateExecutor<MaterialTest> {

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
}
