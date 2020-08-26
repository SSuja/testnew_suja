package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.dto.report.MaterialAcceptedValueDto;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.MainType;

public class TestConfigureDto {
  private Long id;
  private MainType testType;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private AcceptedType acceptedType;
  private String prefix;
  private List<AcceptedValuesDto> acceptedValue;
  private List<MaterialAcceptedValueDto> materialAcceptedValue;
  private List<TestParametersDto> testparameters;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private RawMaterialResponseDto rawMaterial;
  private List<TestEquationResponseDto> testEquations;
  private FinishProductAcceptedValuesDto finishProductAcceptedValue;

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

  public AcceptedType getAcceptedType() {
    return acceptedType;
  }

  public void setAcceptedType(AcceptedType acceptedType) {
    this.acceptedType = acceptedType;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public List<AcceptedValuesDto> getAcceptedValue() {
    return acceptedValue;
  }

  public void setAcceptedValue(List<AcceptedValuesDto> acceptedValue) {
    this.acceptedValue = acceptedValue;
  }

  public List<MaterialAcceptedValueDto> getMaterialAcceptedValue() {
    return materialAcceptedValue;
  }

  public void setMaterialAcceptedValue(List<MaterialAcceptedValueDto> materialAcceptedValue) {
    this.materialAcceptedValue = materialAcceptedValue;
  }

  public List<TestParametersDto> getTestparameters() {
    return testparameters;
  }

  public void setTestparameters(List<TestParametersDto> testparameters) {
    this.testparameters = testparameters;
  }

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public RawMaterialResponseDto getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public List<TestEquationResponseDto> getTestEquations() {
    return testEquations;
  }

  public void setTestEquations(List<TestEquationResponseDto> testEquations) {
    this.testEquations = testEquations;
  }

  public FinishProductAcceptedValuesDto getFinishProductAcceptedValue() {
    return finishProductAcceptedValue;
  }

  public void setFinishProductAcceptedValue(
      FinishProductAcceptedValuesDto finishProductAcceptedValue) {
    this.finishProductAcceptedValue = finishProductAcceptedValue;
  }
}
