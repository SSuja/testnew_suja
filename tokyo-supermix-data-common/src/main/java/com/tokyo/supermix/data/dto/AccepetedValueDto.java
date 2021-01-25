package com.tokyo.supermix.data.dto;

import javax.annotation.Nullable;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;

public class AccepetedValueDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private String conditionRange;
  private boolean finalResult;
  @Nullable
  private Long testEquationId;
  @Nullable
  private String testEquationFormula;
  @Nullable
  private Long materialId;
  @Nullable
  private String materialName;
  private Long testParameterId;
  @Nullable
  private String testParameterName;
  private String parameterName;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private CategoryAcceptedType categoryAcceptedType;

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

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(String conditionRange) {
    this.conditionRange = conditionRange;
  }

  public Long getTestEquationId() {
    return testEquationId;
  }

  public void setTestEquationId(Long testEquationId) {
    this.testEquationId = testEquationId;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public Long getMaterialId() {
    return materialId;
  }

  public void setMaterialId(Long materialId) {
    this.materialId = materialId;
  }

  public String getMaterialName() {
    return materialName;
  }

  public void setMaterialName(String materialName) {
    this.materialName = materialName;
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

  public String getTestEquationFormula() {
    return testEquationFormula;
  }

  public void setTestEquationFormula(String testEquationFormula) {
    this.testEquationFormula = testEquationFormula;
  }

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

  public String getMaterialSubCategoryName() {
    return materialSubCategoryName;
  }

  public void setMaterialSubCategoryName(String materialSubCategoryName) {
    this.materialSubCategoryName = materialSubCategoryName;
  }

  public CategoryAcceptedType getCategoryAcceptedType() {
    return categoryAcceptedType;
  }

  public void setCategoryAcceptedType(CategoryAcceptedType categoryAcceptedType) {
    this.categoryAcceptedType = categoryAcceptedType;
  }
}
