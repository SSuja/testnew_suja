package com.tokyo.supermix.data.dto.report;

public class AcceptedValueDto {
  private Double minValue;
  private Double maxValue;
  private String testName;
  private String unit;
  public Double getMinValue() {
    return minValue;
  }
  public void setMinValue(Double minValue) {
    this.minValue = minValue;
  }
  public Double getMaxValue() {
    return maxValue;
  }
  public void setMaxValue(Double maxValue) {
    this.maxValue = maxValue;
  }
  public String getTestName() {
    return testName;
  }
  public void setTestName(String testName) {
    this.testName = testName;
  }
  public String getUnit() {
    return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }
}
