package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.TestParameter;

public interface TestParameterService {
  public List<TestParameter> saveTestParameter(List<TestParameter> testParameter);

  public TestParameter updateTestParameter(TestParameter testParameter);

  public List<TestParameter> getAllTestParameters();

  boolean isTestParameterExist(Long id);

  public TestParameter getTestParameterById(Long id);

  public void deleteTestParameter(Long id);

  public List<TestParameter> getTestParameterByTestId(Long test);

  public boolean isTestIdExist(Long id);

  public boolean isDuplicateRowExists(Long parameterId, Long testId, Long unitId);
}
