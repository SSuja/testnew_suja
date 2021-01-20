package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;

public class ParameterResultRequestDto {
  private Long id;
  private MaterialTestTrialRequestDto materialTestTrial;
  private Double value;
  private Long testParameterId;
  private String materialTestCode;
  private Long testEquationId;
  private LocalDateTime dateValue;

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

  public String getMaterialTestCode() {
    return materialTestCode;
  }

  public void setMaterialTestCode(String materialTestCode) {
    this.materialTestCode = materialTestCode;
  }

  public Long getTestEquationId() {
    return testEquationId;
  }

  public void setTestEquationId(Long testEquationId) {
    this.testEquationId = testEquationId;
  }

  public LocalDateTime getDateValue() {
    return dateValue;
  }

  public void setDateValue(LocalDateTime dateValue) {
    this.dateValue = dateValue;
  }
}
