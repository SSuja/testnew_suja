package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;

public class FinishProductTestRequestDto {
  private String code;
  private double result;
  private Status status;
  private String finishProductSampleCode;
  private Long testConfigureId;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
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
}
