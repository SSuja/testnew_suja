package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class MaterialTestResponseDto {

  private String code;
  private Date date;
  private Long noOfTrial;
  private Double average;
  private String status;
  private String testLevel;
  private String incomingSampleCode;
  private Long testConfigureId;
  private String testName;
  private Long materialStateId;
  private String materialState;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
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

  public String getIncomingSampleCode() {
    return incomingSampleCode;
  }

  public void setIncomingSampleCode(String incomingSampleCode) {
    this.incomingSampleCode = incomingSampleCode;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
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

}
