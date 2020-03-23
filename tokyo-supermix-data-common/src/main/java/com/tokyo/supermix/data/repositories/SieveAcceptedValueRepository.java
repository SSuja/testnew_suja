package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;

public interface SieveAcceptedValueRepository extends JpaRepository<SieveAcceptedValue, Long> {
  boolean existsBySieveSizeId(Long sieveSizeId);
}
