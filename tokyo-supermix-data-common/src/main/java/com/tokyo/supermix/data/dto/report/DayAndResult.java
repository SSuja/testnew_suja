package com.tokyo.supermix.data.dto.report;

public class DayAndResult {
  private Long day;
  private String testName;
  private double result;

  public Long getDay() {
    return day;
  }

  public void setDay(Long day) {
    this.day = day;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
  }
}
