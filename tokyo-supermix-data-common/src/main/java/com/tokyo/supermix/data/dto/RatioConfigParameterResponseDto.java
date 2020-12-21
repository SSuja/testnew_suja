package com.tokyo.supermix.data.dto;

public class RatioConfigParameterResponseDto {

  private Long id;
  private Long ratioConfigId;
  private String ratioConfigName;
  private Long rawMaterialId;
  private String rawMaterialName;
  private String abbreviation;
  private Long unitId;
  private String unitUnit;
  private Double value;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRatioConfigId() {
    return ratioConfigId;
  }

  public void setRatioConfigId(Long ratioConfigId) {
    this.ratioConfigId = ratioConfigId;
  }

  public String getRatioConfigName() {
    return ratioConfigName;
  }

  public void setRatioConfigName(String ratioConfigName) {
    this.ratioConfigName = ratioConfigName;
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

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
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

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }
}
