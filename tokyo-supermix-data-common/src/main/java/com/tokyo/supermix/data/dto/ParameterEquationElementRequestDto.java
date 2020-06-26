package com.tokyo.supermix.data.dto;

public class ParameterEquationElementRequestDto {
  private Long id;
  private ParameterEquationResponseDto parameterEquation;
  private Long testParameterId;

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

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }
}
