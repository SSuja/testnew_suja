package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class MaterialResult {
  private String testParameterName;
  private String testName;
  private Double average;
  private List<AcceptedValueDto> acceptanceCriterias;

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

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public List<AcceptedValueDto> getAcceptanceCriterias() {
    return acceptanceCriterias;
  }

  public void setAcceptanceCriterias(List<AcceptedValueDto> acceptanceCriterias) {
    this.acceptanceCriterias = acceptanceCriterias;
  }
}
