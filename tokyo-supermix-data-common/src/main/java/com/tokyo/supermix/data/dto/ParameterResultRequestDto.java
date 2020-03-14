package com.tokyo.supermix.data.dto;

public class ParameterResultRequestDto {
  private Long id;
  private String materialTestTrialCode;
  private Double value;
  private Long testParameterId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMaterialTestTrialCode() {
    return materialTestTrialCode;
  }

  public void setMaterialTestTrialCode(String materialTestTrialCode) {
    this.materialTestTrialCode = materialTestTrialCode;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }
}
