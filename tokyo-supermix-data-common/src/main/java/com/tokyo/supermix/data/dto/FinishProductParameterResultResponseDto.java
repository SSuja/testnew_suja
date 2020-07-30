package com.tokyo.supermix.data.dto;

public class FinishProductParameterResultResponseDto {
  private Long id;
  private Double result;
  private TestParameterResponseDto testParameter;
  private FinishProductTestResponseDto finishProductTest;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }

  public FinishProductTestResponseDto getFinishProductTest() {
    return finishProductTest;
  }

  public void setFinishProductTest(FinishProductTestResponseDto finishProductTest) {
    this.finishProductTest = finishProductTest;
  }
}
