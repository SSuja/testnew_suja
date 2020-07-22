package com.tokyo.supermix.data.dto;

public class TestEquationParameterResponseDto {
  private Long id;
  private TestParameterResponseDto testParameter;
  private TestEquationResponseDto testEquation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }

  public TestEquationResponseDto getTestEquation() {
    return testEquation;
  }

  public void setTestEquation(TestEquationResponseDto testEquation) {
    this.testEquation = testEquation;
  }
}
