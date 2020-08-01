package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;

public class FinishProductTestRequestDto {
  private String code;
  private Status status;
  private String finishProductSampleCode;
  private Long testConfigureId;
  private Long noOfTrial;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getFinishProductSampleCode() {
    return finishProductSampleCode;
  }

  public void setFinishProductSampleCode(String finishProductSampleCode) {
    this.finishProductSampleCode = finishProductSampleCode;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }
}