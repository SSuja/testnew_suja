package com.tokyo.supermix.data.dto.report;

import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.Status;

public class MaterialTestDto {
  private Status status;
  private Long testConfigId;
  private String materialTestCode;
  private String createdDate;
  private String updatedDate;
  private String testName;
  private String incomingSampleCode;
  private MainType mainType;
  private Long rawMaterialId;
  private String specimenCode;

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

  public String getMaterialTestCode() {
    return materialTestCode;
  }

  public void setMaterialTestCode(String materialTestCode) {
    this.materialTestCode = materialTestCode;
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

  public String getIncomingSampleCode() {
    return incomingSampleCode;
  }

  public void setIncomingSampleCode(String incomingSampleCode) {
    this.incomingSampleCode = incomingSampleCode;
  }

  public MainType getMainType() {
    return mainType;
  }

  public void setMainType(MainType mainType) {
    this.mainType = mainType;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public String getSpecimenCode() {
    return specimenCode;
  }

  public void setSpecimenCode(String specimenCode) {
    this.specimenCode = specimenCode;
  }
}
