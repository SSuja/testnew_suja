package com.tokyo.supermix.data.dto;

public class MultiResultFormulaParameterDto {

  private Long id;
  private Long testConfigureId;
  private Long testParameterId;
  private Long testParameterName;
  private String abbreviation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public Long getTestParameterName() {
    return testParameterName;
  }

  public void setTestParameterName(Long testParameterName) {
    this.testParameterName = testParameterName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
}
