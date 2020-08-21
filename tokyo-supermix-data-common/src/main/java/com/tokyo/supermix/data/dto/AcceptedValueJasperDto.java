package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;

public class AcceptedValueJasperDto {
  private String minValue;
  private Condition condition;
  private String maxValue;
  private Double value;
  private String material;
  public String getMinValue() {
    return minValue;
  }
  public void setMinValue(String minValue) {
    this.minValue = minValue;
  }
  public Condition getCondition() {
    return condition;
  }
  public void setCondition(Condition condition) {
    this.condition = condition;
  }
  public String getMaxValue() {
    return maxValue;
  }
  public void setMaxValue(String maxValue) {
    this.maxValue = maxValue;
  }
  public Double getValue() {
    return value;
  }
  public void setValue(Double value) {
    this.value = value;
  }
  public String getMaterial() {
    return material;
  }
  public void setMaterial(String material) {
    this.material = material;
  }
  
  
}
