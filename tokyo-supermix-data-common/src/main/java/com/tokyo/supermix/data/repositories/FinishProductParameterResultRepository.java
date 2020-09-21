package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;

public interface FinishProductParameterResultRepository
    extends JpaRepository<FinishProductParameterResult, Long> {
  List<FinishProductParameterResult> findByTestParameterIdAndFinishProductTestCodeOrderByUpdatedAtDesc(
      Long testParameterId, String finishProductTestCode);

  List<FinishProductParameterResult> findByFinishProductTestCodeOrderByUpdatedAtDesc(
      String finishProductTestCode);

  List<FinishProductParameterResult> findByFinishProductTestFinishProductSampleCode(
      String finishProductSampleCode);

  FinishProductParameterResult findByTestParameterIdAndFinishProductTestCode(Long testParameterId,
      String finishProductTestCode);

  List<FinishProductParameterResult> findByFinishProductTestCode(String finishProductTestCode);

  boolean existsByFinishProductTestCode(String finishProductTestCode);
}
