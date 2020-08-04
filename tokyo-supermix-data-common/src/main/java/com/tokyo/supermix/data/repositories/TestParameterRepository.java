package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.MixDesignField;

@Repository
public interface TestParameterRepository
    extends JpaRepository<TestParameter, Long>, QuerydslPredicateExecutor<TestParameter> {
  List<TestParameter> findByTestConfigureId(Long testConfigureId);

  public boolean existsByTestConfigureId(Long testConfigureId);

  public boolean existsByParameterId(Long parameterId);

  public boolean existsByTestConfigureIdAndAbbreviationAndParameterId(Long testConfigureId,
      String abbreviation, Long parameterId);

  public boolean existsByTestConfigureIdAndParameterIdAndUnitIdAndAbbreviation(Long testConfigureId,
      Long qualityParameterId, Long unitId, String abbreviation);

  public List<TestParameter> findAllByOrderByIdDesc();

  public boolean existsByAbbreviation(String abbreviation);

  List<TestParameter> findByTestConfigureIdAndInputMethods(Long testConfigureId,
      InputMethod inputMethod);

  List<TestParameter> findByTestConfigureIdAndLevel(Long testConfigId, String level);

  public boolean existsByTestConfigureIdAndParameterId(Long testConfigureId, Long parameterId);

  TestParameter findByMixDesignField(MixDesignField mixDesignField);

}
