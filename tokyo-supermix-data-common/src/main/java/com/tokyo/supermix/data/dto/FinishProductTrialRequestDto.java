package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class FinishProductTrialRequestDto {
  private Long id;
  private Long trialNo;
  private Date date;
  private Double value;
  private String finishProductTestCode;
  private Long testParameterId;
  private String dateValue;

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

  public String getDateValue() {
    return dateValue;
  }

  public void setDateValue(String dateValue) {
    this.dateValue = dateValue;
  }
}
