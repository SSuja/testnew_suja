package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface MaterialAcceptedValueRepository
    extends JpaRepository<MaterialAcceptedValue, Long> {
  List<MaterialAcceptedValue> findByTestConfigure(TestConfigure testConfigure);

  public boolean existsByTestConfigureIdAndRawMaterialId(Long testConfigureId, Long rawMaterialId);

  List<MaterialAcceptedValue> findByTestConfigureId(Long testConfigureId);

  boolean existsByRawMaterialId(Long rawMaterialId);

  MaterialAcceptedValue findByTestConfigureIdAndRawMaterialId(Long testConfigureId,
      Long rawMaterialId);

  MaterialAcceptedValue findByTestConfigureIdAndTestEquationId(Long testConfigureId,
      Long testEquationId);

  MaterialAcceptedValue findByTestConfigureIdAndRawMaterialIdAndTestEquationId(Long testConfigureId,
      Long rawMaterialId, Long testEquationId);

  public boolean existsByTestConfigureIdAndRawMaterialIdAndTestParameterId(Long testConfigureId,
      Long rawMaterialId, Long testParameterId);
}
