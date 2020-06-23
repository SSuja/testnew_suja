package com.tokyo.supermix.data.dto;

public class ParameterEquationDto {
  private Long testParameterId;
  private String parameterEquation;
  private String abbreviation;

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public String getParameterEquation() {
    return parameterEquation;
  }

  public void setParameterEquation(String parameterEquation) {
    this.parameterEquation = parameterEquation;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
}
