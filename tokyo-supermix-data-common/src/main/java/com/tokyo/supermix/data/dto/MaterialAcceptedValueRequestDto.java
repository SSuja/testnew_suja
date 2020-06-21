package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;

public class MaterialAcceptedValueRequestDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  @NotNull(message = "{materialAcceptedValueRequestDto.rawMaterialId.null}")
  private Long rawMaterialId;
  @NotNull(message = "{materialAcceptedValueRequestDto.testConfigureId.null}")
  private Long testConfigureId;
  private Long unitId;

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

  public Long getUnitId() {
    return unitId;
  }

  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }

}