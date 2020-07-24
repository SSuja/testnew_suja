package com.tokyo.supermix.data.dto;

import java.util.List;

import com.tokyo.supermix.data.dto.report.AcceptedValueDto;
import com.tokyo.supermix.data.enums.TestType;

public class TestConfigureDto {
  private Long id;
  private TestType testType;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private String prefix;
  private AcceptedValueDto acceptedValue;
  private List<TestParameterDto> testparameters;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private List<TestEquationResponseDto> testEquation;
  private List<ParameterEquationResponseDto> parameterEquation;

  public List<TestEquationResponseDto> getTestEquation() {
    return testEquation;
  }

  public void setTestEquation(List<TestEquationResponseDto> testEquation) {
    this.testEquation = testEquation;
  }

  public List<ParameterEquationResponseDto> getParameterEquation() {
    return parameterEquation;
  }

  public void setParameterEquation(List<ParameterEquationResponseDto> parameterEquation) {
    this.parameterEquation = parameterEquation;
  }

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

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

}
