package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ParameterEquation;

public interface ParameterEquationRepository extends JpaRepository<ParameterEquation, Long> {
  public boolean existsByEquationFormulaAndTestParameterIdAndTestParameterTestConfigureId(
      String formula, Long testParameterId, Long testConfigureId);

  public boolean existsById(Long id);

  public boolean existsByTestParameterId(Long testParameterId);

  public List<ParameterEquation> findByTestParameterTestConfigureId(Long testConfigureId);

  ParameterEquation findByTestParameterId(Long testParameterId);

  public boolean existsByEquationId(Long equationId);

  public ParameterEquation findByTestParameterIdAndTestParameterTestConfigureId(
      Long testParameterId, Long testConfigureId);

  boolean existsByTestParameterTestConfigureId(Long testConfigureId);

  Long deleteByTestParameterTestConfigureId(Long testConfigureId);
}
