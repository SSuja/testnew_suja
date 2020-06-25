package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.TestType;

public class TestConfigureResponseDto {
  private Long id;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private Long testId;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private String prefix;
  private TestType testType;
  private EquationResponseDto equation;
  private boolean equationExists;
  private String resultLabel;
  private boolean bulkTrial;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public TestType getTestType() {
    return testType;
  }

  public void setTestType(TestType testType) {
    this.testType = testType;
  }

  public EquationResponseDto getEquation() {
    return equation;
  }

  public void setEquation(EquationResponseDto equation) {
    this.equation = equation;
  }

  public String getResultLabel() {
    return resultLabel;
  }

  public void setResultLabel(String resultLabel) {
    this.resultLabel = resultLabel;
  }

  public boolean isEquationExists() {
    return equationExists;
  }

  public void setEquationExists(boolean equationExists) {
    this.equationExists = equationExists;
  }

  public boolean isBulkTrial() {
    return bulkTrial;
  }

  public void setBulkTrial(boolean bulkTrial) {
    this.bulkTrial = bulkTrial;
  }
}
