package com.tokyo.supermix.data.dto;

public class ParameterResultResponseDto {
  private Long id;
  private Double value;
  private TestParameterResponseDto testParameterResponseDto;
  private MaterialTestTrialResponseDto materialTestTrialResponseDto;

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

  public TestParameterResponseDto getTestParameterResponseDto() {
    return testParameterResponseDto;
  }

  public void setTestParameterResponseDto(TestParameterResponseDto testParameterResponseDto) {
    this.testParameterResponseDto = testParameterResponseDto;
  }

  public MaterialTestTrialResponseDto getMaterialTestTrialResponseDto() {
    return materialTestTrialResponseDto;
  }

  public void setMaterialTestTrialResponseDto(
      MaterialTestTrialResponseDto materialTestTrialResponseDto) {
    this.materialTestTrialResponseDto = materialTestTrialResponseDto;
  }
}
