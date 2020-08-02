package com.tokyo.supermix.server.services;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.TestParameterEquationDto;
import com.tokyo.supermix.data.entities.TestParameter;

public interface TestParameterService {
  public void saveTestParameter(TestParameter testParameter);

  public List<TestParameter> getAllTestParameters();

  boolean isTestParameterExist(Long id);

  public TestParameter getTestParameterById(Long id);

  public void deleteTestParameter(Long id);

  public boolean isTestConfigureIdExist(Long id);

  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, String abbreviation,
      Long parameterId);

  public Page<TestParameter> searchTestParameter(Predicate predicate, int size, int page);

  public List<TestParameter> getAllTestParametersByTestConfigureId(Long testConfigureId);

  public boolean isAbbreviationNull(String abbreviation);

  public List<TestParameter> getAllParametersByDecending();

  public boolean isParameterIdExist(Long parameterId);

  public boolean isAbbreviationExists(String abbreviation);

  public TestParameterEquationDto getTestParameterEquation(Long testConfigureId);

  public Set<String> getAllOriLevel(Long testConfigId);

  public boolean isParameterExists(Long testConfigureId, Long parameterId);

  public boolean isUpdatedExists(Long id, Long testConfigureId, Long parameterId);
}
