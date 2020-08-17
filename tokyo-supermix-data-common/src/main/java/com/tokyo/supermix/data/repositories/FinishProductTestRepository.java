package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductTest;

public interface FinishProductTestRepository extends JpaRepository<FinishProductTest, String> {

  List<FinishProductTest> findByTestConfigureId(Long testConfigureId);

  boolean existsByTestConfigureId(Long testConfigureId);

  boolean existsByCode(String code);

  FinishProductTest findFinishProductTestByCode(String code);

  List<FinishProductTest> findByCodeContaining(String code);

  List<FinishProductTest> findByFinishProductSampleCode(String finishProductSampleCode);

  List<FinishProductTest> findByFinishProductSampleCodeAndTestConfigureId(
      String finishProductSampleCode, Long testConfigureId);

  List<FinishProductTest> findByTestConfigureTestName(String testName);

  boolean existsByFinishProductSampleCodeAndTestConfigureId(String finishProductSampleCode,
      Long testConfigureId);

  List<FinishProductTest> findByFinishProductSampleMixDesignPlantCodeInOrderByUpdatedAtDesc(
      List<String> plantCodes);

  boolean existsByFinishProductSampleCode(String finishProductSampleCode);

  List<FinishProductTest> findByFinishProductSampleMixDesignPlantCode(String plantCode);

  FinishProductTest findByCodeAndFinishProductSampleMixDesignPlantCode(String finishProductTestCode,
      String plantCode);

  boolean existsByFinishProductSampleMixDesignPlantCode(String plantCode);
}
