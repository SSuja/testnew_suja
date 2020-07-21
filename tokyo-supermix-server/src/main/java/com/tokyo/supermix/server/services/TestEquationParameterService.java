package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.TestEquationParameter;

public interface TestEquationParameterService {
  public List<TestEquationParameter> getAllTestEquationParameters();

  public List<TestEquationParameter> getByTestParameter(Long testParameterId);

  public List<TestEquationParameter> getByTestEquation(Long testEquationId);
}
