package com.tokyo.supermix.data.dto;

import java.util.List;

public class TestEquationDto {
  private Long testConfigId;
  private Long equationId;
  private List<TestEquationParameterDto> testEquationParameters;

  public Long getTestConfigId() {
    return testConfigId;
  }

  public void setTestConfigId(Long testConfigId) {
    this.testConfigId = testConfigId;
  }

  public Long getEquationId() {
    return equationId;
  }

  public void setEquationId(Long equationId) {
    this.equationId = equationId;
  }

  public List<TestEquationParameterDto> getTestEquationParameters() {
    return testEquationParameters;
  }

  public void setTestEquationParameters(List<TestEquationParameterDto> testEquationParameters) {
    this.testEquationParameters = testEquationParameters;
  }
}
