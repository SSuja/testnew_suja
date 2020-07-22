package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParameterResultRequestDto {
  private Long id;
  private MaterialTestTrialRequestDto materialTestTrial;
  @NotNull(message = "{parameterResultDto.value.null}")
  @NotEmpty(message = "{parameterResultDto.value.empty}")
  private Double value;
  private Long testParameterId;
  private String materialTestCode;
  private Long testEquationId;

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
}
