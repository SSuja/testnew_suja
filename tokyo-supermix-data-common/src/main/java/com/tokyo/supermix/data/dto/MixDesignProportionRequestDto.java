package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;

public class MixDesignProportionRequestDto {
  private Long id;
  @NotNull(message = "{mixDesignProportionRequestDto.quantity.null}")
  private Double quantity;
  private Long unitId;
  private String mixDesignCode;
  private Long rawMaterialId;

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
}
