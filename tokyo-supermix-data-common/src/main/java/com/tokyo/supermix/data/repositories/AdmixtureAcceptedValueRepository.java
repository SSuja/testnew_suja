package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.AdmixtureAcceptedValue;

public interface AdmixtureAcceptedValueRepository
    extends JpaRepository<AdmixtureAcceptedValue, Long> {
  boolean existsByTestConfigureId(Long testConfigureId);

  AdmixtureAcceptedValue findByTestConfigureId(Long testConfigureId);

  AdmixtureAcceptedValue findByTestConfigureIdAndRawMaterialId(Long testConfigureId,
      Long materialId);

}
