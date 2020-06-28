package com.tokyo.supermix.data.dto;

public class ParameterResultValueDto {
  private Double value;
  private String testParameterName;

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getTestParameterName() {
    return testParameterName;
  }

  public void setTestParameterName(String testParameterName) {
    this.testParameterName = testParameterName;
  }
}
