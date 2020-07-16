package com.tokyo.supermix.data.dto;

public class ProcessSampleResponseDto {
  private String code;
  private Long quantity;
  private Long unitId;
  private String unit;
  private Long rawMaterialId;
  private String rawMaterialName;
  private IncomingSampleResponseDto incomingSample;
  private String createdAt;
  private String updatedAt;

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
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

  public IncomingSampleResponseDto getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSampleResponseDto incomingSample) {
    this.incomingSample = incomingSample;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
