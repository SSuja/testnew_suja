package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface MaterialAcceptedValueRepository
    extends JpaRepository<MaterialAcceptedValue, Long> {
  List<MaterialAcceptedValue> findByTestConfigure(TestConfigure testConfigure);

  public boolean existsByTestConfigureIdAndRawMaterialId(Long testConfigureId, Long rawMaterialId);

  MaterialAcceptedValue findByTestConfigureId(Long testConfigureId);

  boolean existsByRawMaterialId(Long rawMaterialId);
}