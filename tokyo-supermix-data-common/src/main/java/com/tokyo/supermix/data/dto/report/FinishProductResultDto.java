package com.tokyo.supermix.data.dto.report;

public class FinishProductResultDto {
  private String testParameterName;
  private Double average;

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

}
