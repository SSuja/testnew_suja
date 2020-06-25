package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class FinishProductTrialRequestDto {
  private Long id;
  private Long trialNo;
  private String testSample;
  private Long testSampleNo;
  private Date date;
  private Double value;
  private Long finishProductTestId;
  private Long testParameterId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public String getTestSample() {
    return testSample;
  }

  public void setTestSample(String testSample) {
    this.testSample = testSample;
  }

  public Long getTestSampleNo() {
    return testSampleNo;
  }

  public void setTestSampleNo(Long testSampleNo) {
    this.testSampleNo = testSampleNo;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Long getFinishProductTestId() {
    return finishProductTestId;
  }

  public void setFinishProductTestId(Long finishProductTestId) {
    this.finishProductTestId = finishProductTestId;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }
}
