package com.tokyo.supermix.data.dto.report;

public class MixDesignProportionDto {
  private String materialName;
  private Long quantity;

  public String getMaterialName() {
    return materialName;
  }

  public void setMaterialName(String materialName) {
    this.materialName = materialName;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
