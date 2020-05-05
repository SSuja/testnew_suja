package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.TestParameter;

public interface TestParameterService {
  public List<TestParameter> saveTestParameter(List<TestParameter> testParameter);

  public TestParameter updateTestParameter(TestParameter testParameter);

  public List<TestParameter> getAllTestParameters();

  boolean isTestParameterExist(Long id);

  public TestParameter getTestParameterById(Long id);

  public void deleteTestParameter(Long id);

  public List<TestParameter> getTestParameterByTestConfigureId(Long testConfigureId);

  public boolean isTestConfigureIdExist(Long id);

  public boolean isDuplicateEntryExist(Long testConfigureId, Long parameterId, Long unitId);

  public Page<TestParameter> searchTestParameter(Predicate predicate, int size, int page);
}
