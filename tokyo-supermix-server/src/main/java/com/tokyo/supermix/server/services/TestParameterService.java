package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.TestParameterEquationDto;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.TestParameterType;

public interface TestParameterService {
  public void saveTestParameter(TestParameter testParameter);

  public List<TestParameter> getAllTestParameters();

  boolean isTestParameterExist(Long id);

  public TestParameter getTestParameterById(Long id);

  public void deleteTestParameter(Long id);

  public boolean isTestConfigureIdExist(Long id);

<<<<<<< HEAD
  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, String abbreviation);
=======
  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, Long parameterId,
      Long unitId, String abbreviation, TestParameterType entryLevel);
>>>>>>> a7160384f70ad396b4907c9001a3a2633500b4fc

  public Page<TestParameter> searchTestParameter(Predicate predicate, int size, int page);

  public List<TestParameter> getAllTestParametersByTestConfigureId(Long testConfigureId);

  public boolean isAbbreviationNull(String abbreviation);

  public List<TestParameter> getAllParametersByDecending();

  public boolean isParameterIdExist(Long parameterId);

  public boolean isAbbreviationExists(String abbreviation);

  public TestParameterEquationDto getTestParameterEquation(Long testConfigureId);
}
