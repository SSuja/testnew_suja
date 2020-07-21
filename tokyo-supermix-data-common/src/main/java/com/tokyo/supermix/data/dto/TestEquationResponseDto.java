package com.tokyo.supermix.data.dto;

public class TestEquationResponseDto {
  private Long id;
  private TestConfigureResponseDto testConfigure;
  private EquationResponseDto equation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TestConfigureResponseDto getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigureResponseDto testConfigure) {
    this.testConfigure = testConfigure;
  }

  public EquationResponseDto getEquation() {
    return equation;
  }

  public void setEquation(EquationResponseDto equation) {
    this.equation = equation;
  }
}
