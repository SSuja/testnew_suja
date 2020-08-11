package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;

public class FinishProductAcceptedValueResponseDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Condition conditionRange;
  private boolean finalResult;
  private TestParameterResponseDto testParameter;
  private TestEquationResponseDto testEquation;

  public Long getId() {
    return id;
  }

  public TestEquationResponseDto getTestEquation() {
    return testEquation;
  }

  public void setTestEquation(TestEquationResponseDto testEquation) {
    this.testEquation = testEquation;
  }

  public void setId(Long id) {
    this.id = id;
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

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }
}
