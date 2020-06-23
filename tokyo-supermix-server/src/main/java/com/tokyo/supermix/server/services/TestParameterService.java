package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.TestParameterEquationDto;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.EntryLevel;

public interface TestParameterService {
  public void saveTestParameter(TestParameter testParameter);

  public List<TestParameter> getAllTestParameters();

  boolean isTestParameterExist(Long id);

  public TestParameter getTestParameterById(Long id);

  public void deleteTestParameter(Long id);

  public boolean isTestConfigureIdExist(Long id);

  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, Long parameterId,
      Long unitId, String abbreviation, EntryLevel entryLevel);

  public Page<TestParameter> searchTestParameter(Predicate predicate, int size, int page);

  public List<TestParameter> getAllTestParametersByTestConfigureId(Long testConfigureId);

  public boolean isAbbreviationNull(String abbreviation);

  public List<TestParameter> getAllParametersByDecending();

  public boolean isParameterIdExist(Long parameterId);

  public boolean isAbbreviationExists(String abbreviation);

  public TestParameterEquationDto getTestParameterEquation(Long testConfigureId);
}
