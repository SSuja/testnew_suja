package com.tokyo.supermix.data.dto;

public class MultiResultFormulaParameterDto {
  private Long testParameterId;
  private String testParameterName;
  private String abbreviation;

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public String getTestParameterName() {
    return testParameterName;
  }

  public void setTestParameterName(String testParameterName) {
    this.testParameterName = testParameterName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
}
