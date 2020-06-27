package com.tokyo.supermix.data.dto;

import java.util.List;

import com.tokyo.supermix.data.dto.report.AcceptedValueDto;
import com.tokyo.supermix.data.enums.TestType;
import com.tokyo.supermix.data.enums.TrailResult;

public class TestConfigureDto {
  private Long id;
  private TestType testType;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private String prefix;
  private String resultLabel;
  private AcceptedValueDto acceptedValue;
  private String formula;
  private List<TestParameterDto> testparameters;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private boolean equationExists;
 
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public AcceptedValueDto getAcceptedValue() {
    return acceptedValue;
  }

  public void setAcceptedValue(AcceptedValueDto acceptedValue) {
    this.acceptedValue = acceptedValue;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public TestType getTestType() {
    return testType;
  }

  public void setTestType(TestType testType) {
    this.testType = testType;
  }

  public List<TestParameterDto> getTestparameters() {
    return testparameters;
  }

  public void setTestparameters(List<TestParameterDto> testparameters) {
    this.testparameters = testparameters;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public boolean isEquationExists() {
    return equationExists;
  }

  public void setEquationExists(boolean equationExists) {
    this.equationExists = equationExists;
  }

  public String getResultLabel() {
    return resultLabel;
  }

  public void setResultLabel(String resultLabel) {
    this.resultLabel = resultLabel;
  }
}
