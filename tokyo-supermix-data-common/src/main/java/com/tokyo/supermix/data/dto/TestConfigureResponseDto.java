package com.tokyo.supermix.data.dto;

public class TestConfigureResponseDto {
  private Long id;
  private String name;
  private TestTypeResponseDto testType;
  private EquationDto equation;
  private Long testId;
  private String testName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TestTypeResponseDto getTestType() {
    return testType;
  }

  public void setTestType(TestTypeResponseDto testType) {
    this.testType = testType;
  }

  public EquationDto getEquation() {
    return equation;
  }

  public void setEquation(EquationDto equation) {
    this.equation = equation;
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

}
