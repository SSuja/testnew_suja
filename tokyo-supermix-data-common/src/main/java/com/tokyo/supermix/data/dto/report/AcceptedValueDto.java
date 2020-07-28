package com.tokyo.supermix.data.dto.report;

import com.tokyo.supermix.data.enums.Condition;

public class AcceptedValueDto {
  private Double minValue;
  private Condition condition;
  private Double maxValue;
  private Double value;

  public Double getMinValue() {
    return minValue;
  }

  public void setMinValue(Double minValue) {
    this.minValue = minValue;
  }

  public Double getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(Double maxValue) {
    this.maxValue = maxValue;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Condition getCondition() {
    return condition;
  }

  public void setCondition(Condition condition) {
    this.condition = condition;
  }
}
