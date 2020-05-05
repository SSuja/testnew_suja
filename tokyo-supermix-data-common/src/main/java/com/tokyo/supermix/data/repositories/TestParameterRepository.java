package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.TestParameter;

@Repository
public interface TestParameterRepository
    extends JpaRepository<TestParameter, Long>, QuerydslPredicateExecutor<TestParameter> {
  List<TestParameter> findByTestConfigureId(Long testConfigureId);

  boolean existsByTestConfigureId(Long testConfigureId);

  boolean existsByTestConfigureIdAndParameterIdAndUnitId(Long testConfigureId, Long parameterId,
      Long unitId);
}
