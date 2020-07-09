package com.tokyo.supermix.data.dto;

import java.util.List;

public class ConcreteStrengthDto {
  private String testName;
  private List<CubeAndResult> cubeAndResult;

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public List<CubeAndResult> getCubeAndResult() {
    return cubeAndResult;
  }

  public void setCubeAndResult(List<CubeAndResult> cubeAndResult) {
    this.cubeAndResult = cubeAndResult;
  }

}
