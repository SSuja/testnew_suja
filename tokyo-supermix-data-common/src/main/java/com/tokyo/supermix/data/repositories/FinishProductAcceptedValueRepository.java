package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.FinishProductAcceptedValue;

public interface FinishProductAcceptedValueRepository
    extends JpaRepository<FinishProductAcceptedValue, Long> {
  List<FinishProductAcceptedValue> findByTestParameterId(Long testParameterId);

  FinishProductAcceptedValue findByTestParameterTestConfigureId(Long testConfigureId);

  boolean existsByTestParameterTestConfigureId(Long testConfigureId);

  boolean existsByTestParameterTestConfigureIdAndTestParameterId(Long testConfigureId,
      Long testParameterId);
}
