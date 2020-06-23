package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MixDesignParameterElement;

public interface MixDesignParameterElementRepository
    extends JpaRepository<MixDesignParameterElement, Long> {
  public boolean existsByMixDesignParameterIdAndRawMaterialId(Long mixDesignParameterId,
      Long rawMaterialId);

  public boolean existsByMixDesignParameterId(Long mixDesignParameterId);
}
