package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Origin;

public class TestOriginDto {
  private Long testId;
  private Long testConfigureId;
  private String testName;
  private Origin testOrigin;
  private boolean coreTest;
  public Long getTestId() {
    return testId;
  }
  public void setTestId(Long testId) {
    this.testId = testId;
  }
  public Long getTestConfigureId() {
    return testConfigureId;
  }
  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }
  public String getTestName() {
    return testName;
  }
  public void setTestName(String testName) {
    this.testName = testName;
  }
  public Origin getTestOrigin() {
    return testOrigin;
  }
  public void setTestOrigin(Origin testOrigin) {
    this.testOrigin = testOrigin;
  }
  public boolean isCoreTest() {
    return coreTest;
  }
  public void setCoreTest(boolean coreTest) {
    this.coreTest = coreTest;
  }
  
}
