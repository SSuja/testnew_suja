package com.tokyo.supermix.data.dto;

public class SieveParameterResultDto {
  private String parameterName;
  private Double value;
  
  public String getParameterName() {
    return parameterName;
  }
  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }
  public Double getValue() {
    return value;
  }
  public void setValue(Double value) {
    this.value = value;
  }
}
