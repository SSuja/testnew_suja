package com.tokyo.supermix.data.dto.report;

import java.sql.Date;

public class FinishProductSampleDto {
  private String code;
  private Date testDate;
  private Date updatedDate;
  private String project;
  private String customer;
  private String rawMaterialName;
  private String mixDesignCode;
  private String status;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getTestDate() {
    return testDate;
  }

  public void setTestDate(Date testDate) {
    this.testDate = testDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }

  public String getMixDesignCode() {
    return mixDesignCode;
  }

  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
