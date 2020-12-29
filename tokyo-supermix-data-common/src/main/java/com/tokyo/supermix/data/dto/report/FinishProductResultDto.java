package com.tokyo.supermix.data.dto.report;

public class FinishProductResultDto {
  private String testParameterName;
  private Double average;
  private String unit;

  public String getTestParameterName() {
    return testParameterName;
  }

  public void setTestParameterName(String testParameterName) {
    this.testParameterName = testParameterName;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }
}
