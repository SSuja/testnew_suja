package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;

public class FinishProductTestResponseDto {
  private String code;
  private Status status;
  private FinishProductSampleResponseDto finishProductSample;
  private TestConfigureResponseDto testConfigure;
  private Long noOfTrial;
  private String comments;
  private Date date;

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

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
