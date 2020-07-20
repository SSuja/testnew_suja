package com.tokyo.supermix.data.dto;

public class MaterialTestResponseDto {
  private String code;
  private String createdAt;
  private String updatedAt;
  private Long noOfTrial;
  private String status;
  private String testLevel;
  private IncomingSampleResponseDto incomingSample;
  private TestConfigureResponseDto testConfigure;
  private Long materialStateId;
  private String materialState;
  private String specimenCode;
  private String comment;

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTestLevel() {
    return testLevel;
  }

  public void setTestLevel(String testLevel) {
    this.testLevel = testLevel;
  }

  public IncomingSampleResponseDto getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSampleResponseDto incomingSample) {
    this.incomingSample = incomingSample;
  }

  public TestConfigureResponseDto getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigureResponseDto testConfigure) {
    this.testConfigure = testConfigure;
  }

  public Long getMaterialStateId() {
    return materialStateId;
  }

  public void setMaterialStateId(Long materialStateId) {
    this.materialStateId = materialStateId;
  }

  public String getMaterialState() {
    return materialState;
  }

  public void setMaterialState(String materialState) {
    this.materialState = materialState;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getSpecimenCode() {
    return specimenCode;
  }

  public void setSpecimenCode(String specimenCode) {
    this.specimenCode = specimenCode;
  }

}
