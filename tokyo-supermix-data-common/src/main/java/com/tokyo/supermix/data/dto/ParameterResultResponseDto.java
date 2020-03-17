package com.tokyo.supermix.data.dto;

public class ParameterResultResponseDto {
  private Long id;
  private Double value;
  private TestParameterResponseDto testParameter;
  private MaterialTestTrialResponseDto materialTestTrial;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }

  public MaterialTestTrialResponseDto getMaterialTestTrial() {
    return materialTestTrial;
  }

  public void setMaterialTestTrial(MaterialTestTrialResponseDto materialTestTrial) {
    this.materialTestTrial = materialTestTrial;
  }
}
