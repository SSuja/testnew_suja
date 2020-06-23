package com.tokyo.supermix.data.dto;

import java.util.List;

public class TestParameterEquationDto {
  private List<TestParameterResponseDto> testParameters;
  private List<ParameterEquationDto> parameterEquations;

  public List<TestParameterResponseDto> getTestParameters() {
    return testParameters;
  }

  public void setTestParameters(List<TestParameterResponseDto> testParameters) {
    this.testParameters = testParameters;
  }

  public List<ParameterEquationDto> getParameterEquations() {
    return parameterEquations;
  }

  public void setParameterEquations(List<ParameterEquationDto> parameterEquations) {
    this.parameterEquations = parameterEquations;
  }
}
