package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.MainType;

public class TestConfigureResponseDto {
  private Long id;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private Long testId;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private String prefix;
  private MainType testType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public Long getTestId() {
    return testId;
  }

  public void setTestId(Long testId) {
    this.testId = testId;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public boolean isCoreTest() {
    return coreTest;
  }

  public void setCoreTest(boolean coreTest) {
    this.coreTest = coreTest;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTestProcedure() {
    return testProcedure;
  }

  public void setTestProcedure(String testProcedure) {
    this.testProcedure = testProcedure;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public MainType getTestType() {
    return testType;
  }

  public void setTestType(MainType testType) {
    this.testType = testType;
  }
}
