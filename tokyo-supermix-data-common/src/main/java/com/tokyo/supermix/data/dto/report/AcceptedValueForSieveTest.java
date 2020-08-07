package com.tokyo.supermix.data.dto.report;

import com.tokyo.supermix.data.enums.Condition;

public class AcceptedValueForSieveTest {
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Condition conditionRange;

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

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }

}
