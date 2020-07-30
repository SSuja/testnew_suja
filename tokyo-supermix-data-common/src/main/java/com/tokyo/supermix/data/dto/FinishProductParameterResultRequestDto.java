package com.tokyo.supermix.data.dto;

public class FinishProductParameterResultRequestDto {
  private Long id;
  private Double result;
  private Long testParameterId;
  private Long finishProductTestCode;

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

  public Long getTestParameterId() {
    return testParameterId;
  }

  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }

  public Long getFinishProductTestCode() {
    return finishProductTestCode;
  }

  public void setFinishProductTestCode(Long finishProductTestCode) {
    this.finishProductTestCode = finishProductTestCode;
  }
}
