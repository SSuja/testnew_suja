package com.tokyo.supermix.data.dto;

public class MixDesignProportionResponseDto {
  private Long id;
  private Double quantity;
  private Long unitId;
  private String unit;
  private String mixDesignCode;
  private Long rawMaterialId;
  private String rawMaterialName;
  private String rawMaterialSubCategoryName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Long getUnitId() {
    return unitId;
  }

  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getMixDesignCode() {
    return mixDesignCode;
  }

  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
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

  public String getRawMaterialSubCategoryName() {
    return rawMaterialSubCategoryName;
  }

  public void setRawMaterialSubCategoryName(String rawMaterialSubCategoryName) {
    this.rawMaterialSubCategoryName = rawMaterialSubCategoryName;
  }
}
