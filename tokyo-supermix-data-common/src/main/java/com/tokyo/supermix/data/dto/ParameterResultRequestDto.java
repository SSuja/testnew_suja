package com.tokyo.supermix.data.dto;

public class ParameterResultRequestDto {
  private Long id;
  private MaterialTestTrialRequestDto materialTestTrial;
  private Double value;
  private Long testParameterId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MaterialTestTrialRequestDto getMaterialTestTrial() {
    return materialTestTrial;
  }

  public void setMaterialTestTrial(MaterialTestTrialRequestDto materialTestTrial) {
    this.materialTestTrial = materialTestTrial;
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
