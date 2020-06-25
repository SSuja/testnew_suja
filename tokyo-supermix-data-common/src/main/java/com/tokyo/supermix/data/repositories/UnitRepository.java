package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
  boolean existsByUnit(String unit);

  Unit findByUnit(String unit);
}
