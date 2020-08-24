package com.tokyo.supermix.data.dto;

import javax.annotation.Nullable;

public class TestConfigureResDto {
  private Long testConfigureId;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private String testName;
  private Long materialCategoryId;
  private String materialCategoryName;
  @Nullable
  private Long rawMaterialId;
  @Nullable
  private String rawMaterialName;
  private String acceptedType;
  private String testType;
  public Long getTestConfigureId() {
    return testConfigureId;
  }
  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }
  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }
  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }
  public String getMaterialSubCategoryName() {
    return materialSubCategoryName;
  }
  public void setMaterialSubCategoryName(String materialSubCategoryName) {
    this.materialSubCategoryName = materialSubCategoryName;
  }
  public String getTestName() {
    return testName;
  }
  public void setTestName(String testName) {
    this.testName = testName;
  }
  public Long getMaterialCategoryId() {
    return materialCategoryId;
  }
  public void setMaterialCategoryId(Long materialCategoryId) {
    this.materialCategoryId = materialCategoryId;
  }
  public String getMaterialCategoryName() {
    return materialCategoryName;
  }
  public void setMaterialCategoryName(String materialCategoryName) {
    this.materialCategoryName = materialCategoryName;
  }
  public Long getRawMaterialId() {
    return rawMaterialId;
  }
  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }
  public String getRawMaterialName() {
    return rawMaterialName;
  }
  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }
  public String getAcceptedType() {
    return acceptedType;
  }
  public void setAcceptedType(String acceptedType) {
    this.acceptedType = acceptedType;
  }
  public String getTestType() {
    return testType;
  }
  public void setTestType(String testType) {
    this.testType = testType;
  }
  
}
