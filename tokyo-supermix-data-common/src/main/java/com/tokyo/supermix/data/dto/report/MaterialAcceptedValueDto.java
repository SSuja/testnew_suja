package com.tokyo.supermix.data.dto.report;

import com.tokyo.supermix.data.enums.Condition;

public class MaterialAcceptedValueDto {
  private String materialName;
  private String materialSubCategoryName;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Condition conditionRange;
  private String testName;
  private boolean finalResult;

  public boolean isFinalResult() {
    return finalResult;
  }

  public void setFinalResult(boolean finalResult) {
    this.finalResult = finalResult;
  }

  public String getMaterialName() {
    return materialName;
  }

  public void setMaterialName(String materialName) {
    this.materialName = materialName;
  }

  public String getMaterialSubCategoryName() {
    return materialSubCategoryName;
  }

  public void setMaterialSubCategoryName(String materialSubCategoryName) {
    this.materialSubCategoryName = materialSubCategoryName;
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

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }
}
