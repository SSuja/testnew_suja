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

  List<FinishProductTest> findByFinishProductSampleId(Long finishProductSampleId);

  boolean existsByFinishProductSampleIdAndTestConfigureId(Long finishProductSampleId,
      Long testConfigureId);
}
