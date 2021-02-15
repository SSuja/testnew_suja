package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.TestEquation;

public interface TestEquationRepository extends JpaRepository<TestEquation, Long> {
  boolean existsByTestConfigureIdAndEquationId(Long testConfigureId, Long equationId);

  public List<TestEquation> findByTestConfigureId(Long testConfigureId);

  public List<TestEquation> findByEquationId(Long equationId);

  TestEquation findByTestParameterId(Long testParameterId);

  boolean existsByEquationId(Long equationId);

  boolean existsByTestParameterId(Long testParameterId);

  public TestEquation findByTestConfigureIdAndTestParameterId(Long testConfigId,
      Long testParameterId);

  boolean existsByTestConfigureId(Long testConfigureId);

  Long deleteByTestConfigureId(Long testConfigureId);
}
