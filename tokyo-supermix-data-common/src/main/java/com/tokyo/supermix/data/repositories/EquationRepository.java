package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Equation;

public interface EquationRepository extends JpaRepository<Equation, Long> {
  boolean existsByFormula(String formula);
}
