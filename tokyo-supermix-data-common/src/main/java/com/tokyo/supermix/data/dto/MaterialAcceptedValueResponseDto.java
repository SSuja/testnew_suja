package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;

public class MaterialAcceptedValueResponseDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private RawMaterialResponseDto rawMaterial;
  private TestConfigureResponseDto testConfigure;
  private Double value;
  private TestParameterResponseDto testParameter;
  private Condition conditionRange;
  private boolean isFinalResult;

  public Long getId() {
    return id;
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

  public RawMaterialResponseDto getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public TestConfigureResponseDto getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigureResponseDto testConfigure) {
    this.testConfigure = testConfigure;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }

  public boolean isFinalResult() {
    return isFinalResult;
  }

  public void setFinalResult(boolean isFinalResult) {
    this.isFinalResult = isFinalResult;
  }

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }
}
