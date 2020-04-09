package com.tokyo.supermix.data.dto;

public class TestConfigureRequestDto {
  private Long id;
  private Long testTypeId;
  private Long equationId;
  private Long testId;
  private boolean coreTest;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTestTypeId() {
    return testTypeId;
  }

  public void setTestTypeId(Long testTypeId) {
    this.testTypeId = testTypeId;
  }

  public Long getEquationId() {
    return equationId;
  }

  public void setEquationId(Long equationId) {
    this.equationId = equationId;
  }

  public Long getTestId() {
    return testId;
  }

  public void setTestId(Long testId) {
    this.testId = testId;
  }

  public boolean isCoreTest() {
    return coreTest;
  }

  public void setCoreTest(boolean coreTest) {
    this.coreTest = coreTest;
  }

}
