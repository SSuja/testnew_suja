package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;

public class FinishProductAcceptedValuesDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Condition conditionRange;
  private boolean finalResult;
  private String testParameterName;
  private String testParameterParameterName;
  private Long testParameterId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

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

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }

  public boolean isFinalResult() {
    return finalResult;
  }

  public void setFinalResult(boolean finalResult) {
    this.finalResult = finalResult;
  }

  public String getTestParameterName() {
    return testParameterName;
  }

  public void setTestParameterName(String testParameterName) {
    this.testParameterName = testParameterName;
  }

  public String getTestParameterParameterName() {
    return testParameterParameterName;
  }

  public void setTestParameterParameterName(String testParameterParameterName) {
    this.testParameterParameterName = testParameterParameterName;
  }
}
