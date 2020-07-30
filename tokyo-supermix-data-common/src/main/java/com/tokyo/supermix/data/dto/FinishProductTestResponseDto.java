package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;

public class FinishProductTestResponseDto {
  private String code;
  private Status status;
  private FinishProductSampleResponseDto finishProductSample;
  private TestConfigureResponseDto testConfigure;

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

  public FinishProductSampleResponseDto getFinishProductSample() {
    return finishProductSample;
  }

  public void setFinishProductSample(FinishProductSampleResponseDto finishProductSample) {
    this.finishProductSample = finishProductSample;
  }

  public TestConfigureResponseDto getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigureResponseDto testConfigure) {
    this.testConfigure = testConfigure;
  }
}
