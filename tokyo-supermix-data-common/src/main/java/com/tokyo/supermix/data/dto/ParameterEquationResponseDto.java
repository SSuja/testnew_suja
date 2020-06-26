package com.tokyo.supermix.data.dto;

public class ParameterEquationResponseDto {
  private Long id;
  private EquationResponseDto equation;
  private TestParameterResponseDto testParameter;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EquationResponseDto getEquation() {
    return equation;
  }

  public void setEquation(EquationResponseDto equation) {
    this.equation = equation;
  }

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }
}
