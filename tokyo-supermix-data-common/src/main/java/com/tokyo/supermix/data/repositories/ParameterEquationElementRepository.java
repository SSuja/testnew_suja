package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ParameterEquationElement;

public interface ParameterEquationElementRepository
    extends JpaRepository<ParameterEquationElement, Long> {
  public boolean existsByParameterEquationIdAndTestParameterId(Long parameterEquationId,
      Long testParameterId);

  public boolean existsById(Long id);

  public List<ParameterEquationElement> findByTestParameterId(Long testParameterId);

  public List<ParameterEquationElement> findByParameterEquationId(Long parameterEquationId);

  public List<ParameterEquationElement> findByParameterEquationTestParameterId(
      Long testParameterId);

  boolean existsByParameterEquationTestParameterTestConfigureId(Long testConfigureid);

  Long deleteByParameterEquationTestParameterTestConfigureId(Long testConfigureid);
}
