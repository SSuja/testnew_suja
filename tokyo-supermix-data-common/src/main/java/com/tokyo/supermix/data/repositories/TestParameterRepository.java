package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.EntryLevel;

@Repository
public interface TestParameterRepository
    extends JpaRepository<TestParameter, Long>, QuerydslPredicateExecutor<TestParameter> {
  List<TestParameter> findByTestConfigureId(Long testConfigureId);

  public boolean existsByTestConfigureId(Long testConfigureId);

  public boolean existsByTestConfigureIdAndParameterIdAndUnitIdAndAbbreviationAndEntryLevel(
      Long testConfigureId, Long parameterId, Long unitId, String abbreviation,
      EntryLevel entryLevel);

  public boolean existsByTestConfigureIdAndParameterIdAndUnitIdAndAbbreviation(Long testConfigureId,
      Long qualityParameterId, Long unitId, String abbreviation);

  public List<TestParameter> findAllByOrderByIdDesc();
}
