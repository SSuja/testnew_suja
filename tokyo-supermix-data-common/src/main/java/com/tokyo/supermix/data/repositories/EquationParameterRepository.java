package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.EquationParameter;

public interface EquationParameterRepository extends JpaRepository<EquationParameter, Long> {
  List<EquationParameter> findByEquationId(Long equationId);

  boolean existsByEquationId(Long equationId);

  boolean existsByEquationIdAndTestParameterId(Long equationId, Long testParameterId);
}
