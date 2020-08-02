package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;

public interface FinishProductParameterResultRepository
    extends JpaRepository<FinishProductParameterResult, Long> {
  // List<FinishProductParameterResult> findByTestParameterIdAndFinishProductSampleCode(
  // Long testParameterId, String finishProductTestCode);

  // List<FinishProductParameterResult> findByFinishProductSampleCode(String
  // finishProductSampleCode);
  FinishProductParameterResult findByTestParameterIdAndFinishProductTestCode(Long testParameterId,String finishProductTestCode);

  List<FinishProductParameterResult> findByFinishProductTestCode(String finishProductTestCode);
}
