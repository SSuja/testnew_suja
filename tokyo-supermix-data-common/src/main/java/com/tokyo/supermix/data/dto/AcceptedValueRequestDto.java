package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Condition;

public class AcceptedValueRequestDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  @NotNull(message = "{acceptedValueRequestDto.testConfigureId.null}")
  private Long testConfigureId;
  private Long testParameterId;
  private Double value;
  private Condition condition;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
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
