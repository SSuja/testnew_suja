package com.tokyo.supermix.data.dto.report;

public class ResultsDto {
  private String parameterName;
  private Double result;

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }
}
