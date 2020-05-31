package com.tokyo.supermix.data.dto.report;

public class TestDetailDto {
  private String testName;
  private Double actualValue;
  private AcceptedValueDto acceptanceCriteria;
  private String status;

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public Double getActualValue() {
    return actualValue;
  }

  public void setActualValue(Double actualValue) {
    this.actualValue = actualValue;
  }

  public AcceptedValueDto getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(AcceptedValueDto acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
