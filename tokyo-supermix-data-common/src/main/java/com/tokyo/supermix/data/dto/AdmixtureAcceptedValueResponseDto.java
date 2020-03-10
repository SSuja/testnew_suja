package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.AcceptedValue;

public class AdmixtureAcceptedValueResponseDto {
  private AcceptedValue acceptedValue;
  private RawMaterialResponseDto rawMaterial;

  public AcceptedValue getAcceptedValue() {
    return acceptedValue;
  }

  public void setAcceptedValue(AcceptedValue acceptedValue) {
    this.acceptedValue = acceptedValue;
  }

  public RawMaterialResponseDto getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
    this.rawMaterial = rawMaterial;
  }
}
