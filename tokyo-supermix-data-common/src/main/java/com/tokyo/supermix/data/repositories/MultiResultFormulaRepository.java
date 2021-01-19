package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MultiResultFormula;

public interface MultiResultFormulaRepository extends JpaRepository<MultiResultFormula, Long> {

  MultiResultFormula findByTestConfigureId(Long testConfigureId);

  boolean existsByTestConfigureId(Long testConfigureId);
}
