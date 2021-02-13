package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.MaterialParamType;

public class MaterialAcceptedValueRequestDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Long rawMaterialId;
  private Long testConfigureId;
  private Long testParameterId;
  private Long testEquationId;
  private Double value;
  private Condition conditionRange;
  private boolean finalResult;
  private Long materialSubCategoryId;
  private CategoryAcceptedType categoryAcceptedType;
  private Long materialQualityParameterId;
  private MaterialParamType materialParamType;

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

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public boolean isFinalResult() {
    return finalResult;
  }

  public void setFinalResult(boolean finalResult) {
    this.finalResult = finalResult;
  }

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }

  public Long getTestEquationId() {
    return testEquationId;
  }

  public void setTestEquationId(Long testEquationId) {
    this.testEquationId = testEquationId;
  }

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

  public CategoryAcceptedType getCategoryAcceptedType() {
    return categoryAcceptedType;
  }

  public void setCategoryAcceptedType(CategoryAcceptedType categoryAcceptedType) {
    this.categoryAcceptedType = categoryAcceptedType;
  }

  public MaterialParamType getMaterialParamType() {
    return materialParamType;
  }

  public void setMaterialParamType(MaterialParamType materialParamType) {
    this.materialParamType = materialParamType;
  }

  public Long getMaterialQualityParameterId() {
    return materialQualityParameterId;
  }

  public void setMaterialQualityParameterId(Long materialQualityParameterId) {
    this.materialQualityParameterId = materialQualityParameterId;
  }
}