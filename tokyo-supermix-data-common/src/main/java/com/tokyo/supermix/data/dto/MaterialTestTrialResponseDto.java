package com.tokyo.supermix.data.dto;

public class MaterialTestTrialResponseDto {
  private String code;
  private Long trialNo;
  private MaterialTestResponseDto materialTest;
  private TestEquationResponseDto testEquation;
  private String createdAt;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
