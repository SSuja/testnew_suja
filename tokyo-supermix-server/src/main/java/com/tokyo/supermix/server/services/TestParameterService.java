package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.EntryLevel;

public interface TestParameterService {
  public List<TestParameter> saveTestParameter(List<TestParameter> testParameter);

  public TestParameter updateTestParameter(TestParameter testParameter);

  public List<TestParameter> getAllTestParameters();

  boolean isTestParameterExist(Long id);

  public TestParameter getTestParameterById(Long id);

  public void deleteTestParameter(Long id);

//  public List<TestParameter> getTestAndQualityParameterByTestConfigureId(Long testConfigureId,
//      String incomingSampleCode);

  public boolean isTestConfigureIdExist(Long id);

  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, Long parameterId,
      Long unitId, String abbreviation, EntryLevel entryLevel);

//  public boolean isDuplicateQualityTestParameterEntryExist(Long testConfigureId, Long unitId,
//      String abbreviation);

  public Page<TestParameter> searchTestParameter(Predicate predicate, int size, int page);

  public List<TestParameter> getAllTestParametersByTestConfigureId(Long testConfigureId);

//  public List<TestParameter> getAllQualityParametersByTestConfigureId(Long testConfigureId);

//  public TestParameter getQualityParameterById(Long testParameterId);

  public boolean isAbbreviationNull(String abbreviation);


}
