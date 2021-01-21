package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;

public class ParameterResultResponseDto {
  private Long id;
  private Double value;
  private LocalDateTime dateValue;
  private TestParameterResponseDto testParameter;
  private MaterialTestTrialResponseDto materialTestTrial;
  private MaterialTestResponseDto materialTest;
  private TestEquationResponseDto testEquation;

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

  public MaterialTestResponseDto getMaterialTest() {
    return materialTest;
  }

  public void setMaterialTest(MaterialTestResponseDto materialTest) {
    this.materialTest = materialTest;
  }

  public TestEquationResponseDto getTestEquation() {
    return testEquation;
  }

  public void setTestEquation(TestEquationResponseDto testEquation) {
    this.testEquation = testEquation;
  }

  public LocalDateTime getDateValue() {
    return dateValue;
  }

  public void setDateValue(LocalDateTime dateValue) {
    this.dateValue = dateValue;
  }
}
