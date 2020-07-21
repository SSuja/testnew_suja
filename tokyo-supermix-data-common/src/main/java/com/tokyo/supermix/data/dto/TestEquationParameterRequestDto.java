package com.tokyo.supermix.data.dto;

public class TestEquationParameterRequestDto {
  private Long id;
  private Long testParameterId;
  private Long testEquationId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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
}
