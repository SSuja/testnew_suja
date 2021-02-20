package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.TestEquationParameter;

public interface TestEquationParameterRepository
    extends JpaRepository<TestEquationParameter, Long> {
  public List<TestEquationParameter> findByTestParameterId(Long testParameterId);

  public List<TestEquationParameter> findByTestEquationId(Long testEquationId);

  public List<TestEquationParameter> findByTestEquationTestConfigureId(Long testConfigureId);

  Long deleteByTestEquationTestConfigureId(Long testConfigureId);

  boolean existsByTestEquationTestConfigureId(Long testConfigureId);

  boolean existsByTestParameterId(Long testParameterId);
}

