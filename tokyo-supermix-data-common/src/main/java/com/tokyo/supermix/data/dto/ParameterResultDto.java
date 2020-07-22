package com.tokyo.supermix.data.dto;

public class ParameterResultDto {
  private Long testParameterId;
  private Long testEquationId;
  private Double value;

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public Long getTestEquationId() {
    return testEquationId;
  }

  public void setTestEquationId(Long testEquationId) {
    this.testEquationId = testEquationId;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }
}
