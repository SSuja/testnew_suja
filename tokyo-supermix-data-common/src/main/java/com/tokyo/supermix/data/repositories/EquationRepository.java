package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.enums.EquationType;

public interface EquationRepository extends JpaRepository<Equation, Long> {

  boolean existsByFormula(String formula);

  List<Equation> findByEquationType(EquationType equationType);

  List<Equation> findByParameterExistsTrue();
}
