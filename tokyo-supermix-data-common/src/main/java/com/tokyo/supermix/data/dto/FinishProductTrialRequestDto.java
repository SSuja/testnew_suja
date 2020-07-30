package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.FinishProductState;

public class FinishProductTrialRequestDto {
  private String code;
  private Long trialNo;
  private FinishProductState testSample;
  private Date date;
  private Double value;
  private String finishProductTestCode;
  private Long testParameterId;

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

  public FinishProductState getTestSample() {
    return testSample;
  }

  public void setTestSample(FinishProductState testSample) {
    this.testSample = testSample;
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

  public String getFinishProductTestCode() {
    return finishProductTestCode;
  }

  public void setFinishProductTestCode(String finishProductTestCode) {
    this.finishProductTestCode = finishProductTestCode;
  }

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }
}
