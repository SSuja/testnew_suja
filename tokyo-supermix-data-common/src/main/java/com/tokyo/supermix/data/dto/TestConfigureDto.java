package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.dto.report.MaterialAcceptedValueDto;
import com.tokyo.supermix.data.enums.MainType;

public class TestConfigureDto {
  private Long id;
  private MainType testType;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private String prefix;
  private List<AcceptedValueResponseDto> acceptedValue;
  private List<MaterialAcceptedValueDto> materialAcceptedValue;
  private List<TestParameterResponseDto> testparameters;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private List<TestEquationResponseDto> testEquations;
  private List<MaterialAcceptedValueDto> rawMaterialDto;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MainType getTestType() {
    return testType;
  }

  public void setTestType(MainType testType) {
    this.testType = testType;
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

  public List<AcceptedValueResponseDto> getAcceptedValue() {
    return acceptedValue;
  }

  public void setAcceptedValue(List<AcceptedValueResponseDto> acceptedValue) {
    this.acceptedValue = acceptedValue;
  }

  public List<MaterialAcceptedValueDto> getMaterialAcceptedValue() {
    return materialAcceptedValue;
  }

  public void setMaterialAcceptedValue(List<MaterialAcceptedValueDto> materialAcceptedValue) {
    this.materialAcceptedValue = materialAcceptedValue;
  }

  public List<TestParameterResponseDto> getTestparameters() {
    return testparameters;
  }

  public void setTestparameters(List<TestParameterResponseDto> testparameters) {
    this.testparameters = testparameters;
  }

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public List<TestEquationResponseDto> getTestEquations() {
    return testEquations;
  }

  public void setTestEquations(List<TestEquationResponseDto> testEquations) {
    this.testEquations = testEquations;
  }

  public List<MaterialAcceptedValueDto> getRawMaterialDto() {
    return rawMaterialDto;
  }

  public void setRawMaterialDto(List<MaterialAcceptedValueDto> rawMaterialDto) {
    this.rawMaterialDto = rawMaterialDto;
  }
}
