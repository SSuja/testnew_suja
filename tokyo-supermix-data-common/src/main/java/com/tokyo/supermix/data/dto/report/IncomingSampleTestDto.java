package com.tokyo.supermix.data.dto.report;

import com.tokyo.supermix.data.enums.Status;

public class IncomingSampleTestDto {
  private String testName;
  private Status status;
  private Double average;

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }
}
