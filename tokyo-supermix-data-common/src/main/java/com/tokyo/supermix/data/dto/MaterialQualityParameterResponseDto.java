package com.tokyo.supermix.data.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;

public class MaterialQualityParameterResponseDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  @Enumerated(EnumType.ORDINAL)
  private UnitDto unit;
  private Condition conditionRange;
  private RawMaterialResponseDto rawMaterial;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private MaterialCategoryDto materialCategory;
  private QualityParameterResponseDto qualityParameter;
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

  public UnitDto getUnit() {
    return unit;
  }

  public void setUnit(UnitDto unit) {
    this.unit = unit;
  }

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }

  public RawMaterialResponseDto getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public MaterialCategoryDto getMaterialCategory() {
    return materialCategory;
  }

  public void setMaterialCategory(MaterialCategoryDto materialCategory) {
    this.materialCategory = materialCategory;
  }

  public QualityParameterResponseDto getQualityParameter() {
    return qualityParameter;
  }

  public void setQualityParameter(QualityParameterResponseDto qualityParameter) {
    this.qualityParameter = qualityParameter;
  }

  public QualityParamaterType getQualityParamaterType() {
    return qualityParamaterType;
  }

  public void setQualityParamaterType(QualityParamaterType qualityParamaterType) {
    this.qualityParamaterType = qualityParamaterType;
  }
}
