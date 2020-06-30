package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ParameterEquation;

public interface ParameterEquationRepository extends JpaRepository<ParameterEquation, Long> {
  public boolean existsByEquationIdAndTestParameterId(Long equationId, Long testParameterId);

  public boolean existsById(Long id);

  public List<ParameterEquation> findByTestParameterTestConfigureId(Long testConfigureId);

  ParameterEquation findByTestParameterId(Long testParameterId);

  public List<ParameterEquation> findByEquationId(Long equationId);
}
