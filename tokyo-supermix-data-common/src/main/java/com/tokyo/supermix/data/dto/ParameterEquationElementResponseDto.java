package com.tokyo.supermix.data.dto;

public class ParameterEquationElementResponseDto {
  private Long id;
  private ParameterEquationResponseDto parameterEquation;
  private TestParameterResponseDto testParameter;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ParameterEquationResponseDto getParameterEquation() {
    return parameterEquation;
  }

  public void setParameterEquation(ParameterEquationResponseDto parameterEquation) {
    this.parameterEquation = parameterEquation;
  }

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }
}
