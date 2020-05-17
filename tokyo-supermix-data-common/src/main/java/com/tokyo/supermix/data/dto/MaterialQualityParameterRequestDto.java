package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;

public class MaterialQualityParameterRequestDto {
  private Long id;
  @NotNull(message = "{materialQualityParameterRequestDto.qualityParameterId.null}")
  private Long qualityParameterId;
  private Double value;
  @NotNull(message = "{materialQualityParameterRequestDto.rawMaterialId.null}")
  private Long rawMaterialId;
  private Long unitId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getQualityParameterId() {
    return qualityParameterId;
  }

  public void setQualityParameterId(Long qualityParameterId) {
    this.qualityParameterId = qualityParameterId;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public Long getUnitId() {
    return unitId;
  }

  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }
}
