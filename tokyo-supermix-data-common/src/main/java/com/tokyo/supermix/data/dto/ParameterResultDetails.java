package com.tokyo.supermix.data.dto;

public class ParameterResultDetails {
  private Long testParameterId;
  private String testParameterInputMethod;
  private String testParameterType;
  private String testParameterName;
  private String TestParameterAbbrebation;
  private Double value;

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public String getTestParameterInputMethod() {
    return testParameterInputMethod;
  }

  public void setTestParameterInputMethod(String testParameterInputMethod) {
    this.testParameterInputMethod = testParameterInputMethod;
  }

  public String getTestParameterType() {
    return testParameterType;
  }

  public void setTestParameterType(String testParameterType) {
    this.testParameterType = testParameterType;
  }

  public String getTestParameterName() {
    return testParameterName;
  }

  public void setTestParameterName(String testParameterName) {
    this.testParameterName = testParameterName;
  }

  public String getTestParameterAbbrebation() {
    return TestParameterAbbrebation;
  }

  public void setTestParameterAbbrebation(String testParameterAbbrebation) {
    TestParameterAbbrebation = testParameterAbbrebation;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

}
