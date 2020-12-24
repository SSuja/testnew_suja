package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.SubBusinessUnit;

public interface SubBusinessUnitRepository extends JpaRepository<SubBusinessUnit, Long> {

  boolean existsByName(String name);
}
