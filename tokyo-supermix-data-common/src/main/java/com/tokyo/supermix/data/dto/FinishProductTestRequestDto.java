package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;

public class FinishProductTestRequestDto {

  private Long id;
  private double result;
  private Status status;
  private Long finishProductSampleId;
  private Long testConfigureId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getFinishProductSampleId() {
    return finishProductSampleId;
  }

  public void setFinishProductSampleId(Long finishProductSampleId) {
    this.finishProductSampleId = finishProductSampleId;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }
}
