package com.tokyo.supermix.data.dto;

public class MaterialQualityParameterResponseDto {
  private Long id;
  private QualityParameterResponseDto qualityParameter;
  private Double value;
  private RawMaterialResponseDto rawMaterial;
  private UnitDto unit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public QualityParameterResponseDto getQualityParameter() {
    return qualityParameter;
  }

  public void setQualityParameter(QualityParameterResponseDto qualityParameter) {
    this.qualityParameter = qualityParameter;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public RawMaterialResponseDto getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public UnitDto getUnit() {
    return unit;
  }

  public void setUnit(UnitDto unit) {
    this.unit = unit;
  }
}
