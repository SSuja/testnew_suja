package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;

public interface FinishProductParameterResultRepository
    extends JpaRepository<FinishProductParameterResult, Long> {
  List<FinishProductParameterResult> findByTestParameterIdAndFinishProductSampleId(
      Long testParameterId, Long finishProductTestId);

  List<FinishProductParameterResult> findByFinishProductSampleId(Long finishProductSampleId);
}