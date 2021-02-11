package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;

public class MaterialQualityParameterResponseDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Long unitId;
  private String unitUnit;
  private Condition conditionRange;
  private Long rawMaterialId;
  private String rawMaterialName;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private Long materialCategoryId;
  private String materialCategoryName;
  private Long qualityParameterId;
  private String qualityParameterName;
  private QualityParamaterType qualityParamaterType;

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

  public Long getUnitId() {
    return unitId;
  }

  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }

  public String getUnitUnit() {
    return unitUnit;
  }

  public void setUnitUnit(String unitUnit) {
    this.unitUnit = unitUnit;
  }

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
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

  public Long getMaterialCategoryId() {
    return materialCategoryId;
  }

  public void setMaterialCategoryId(Long materialCategoryId) {
    this.materialCategoryId = materialCategoryId;
  }

  public String getMaterialCategoryName() {
    return materialCategoryName;
  }

  public void setMaterialCategoryName(String materialCategoryName) {
    this.materialCategoryName = materialCategoryName;
  }

  public Long getQualityParameterId() {
    return qualityParameterId;
  }

  public void setQualityParameterId(Long qualityParameterId) {
    this.qualityParameterId = qualityParameterId;
  }

  public String getQualityParameterName() {
    return qualityParameterName;
  }

  public void setQualityParameterName(String qualityParameterName) {
    this.qualityParameterName = qualityParameterName;
  }

  public QualityParamaterType getQualityParamaterType() {
    return qualityParamaterType;
  }

  public void setQualityParamaterType(QualityParamaterType qualityParamaterType) {
    this.qualityParamaterType = qualityParamaterType;
  }
}
