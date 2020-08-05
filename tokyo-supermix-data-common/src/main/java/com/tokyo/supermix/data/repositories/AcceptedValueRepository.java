package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface AcceptedValueRepository
    extends JpaRepository<AcceptedValue, Long>, QuerydslPredicateExecutor<AcceptedValue> {
  List<AcceptedValue> findByTestConfigure(TestConfigure testConfigure);

  public boolean existsByTestConfigureId(Long testConfigureId);

  public boolean existsByTestConfigureIdAndTestEquationId(Long testConfigureId,
      Long testEquationId);

  List<AcceptedValue> findByTestConfigureId(Long testConfigureId);

  public AcceptedValue findByTestConfigureIdAndTestEquationId(Long testConfigureId,
      Long testEquationId);
}
