package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;

public class FinishProductTestDto {
  private Status status;
  private Long testConfigId;
  private String finishproductTestCode;
  private String createdDate;
  private String updatedDate;
  private String testName;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Long getTestConfigId() {
    return testConfigId;
  }

  public void setTestConfigId(Long testConfigId) {
    this.testConfigId = testConfigId;
  }

  public String getFinishproductTestCode() {
    return finishproductTestCode;
  }

  public void setFinishproductTestCode(String finishproductTestCode) {
    this.finishproductTestCode = finishproductTestCode;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public String getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }
}
