package com.tokyo.supermix.data.dto;

public class AbbrevationAndValueDto {
  private String materialTrialCode;
  private double value;
  public String getMaterialTrialCode() {
    return materialTrialCode;
  }
  public void setMaterialTrialCode(String materialTrialCode) {
    this.materialTrialCode = materialTrialCode;
  }
  public double getValue() {
    return value;
  }
  public void setValue(double value) {
    this.value = value;
  }
}
