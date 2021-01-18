package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MultiResultFormula;

public interface MultiResultFormulaRepository extends JpaRepository<MultiResultFormula, Long> {
  public List<MultiResultFormula> findByTestConfigureId(Long testConfigureId);

  public boolean existsByTestConfigureId(Long testConfigureId);
}
