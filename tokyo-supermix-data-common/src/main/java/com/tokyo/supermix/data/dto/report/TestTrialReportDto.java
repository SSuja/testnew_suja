package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class TestTrialReportDto {
  private String code;
  private Long trialNo;
  private Double result;
  private List<ParameterResultDto> parameterResults;
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public Long getTrialNo() {
    return trialNo;
  }
  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }
  public Double getResult() {
    return result;
  }
  public void setResult(Double result) {
    this.result = result;
  }
  public List<ParameterResultDto> getParameterResults() {
    return parameterResults;
  }
  public void setParameterResults(List<ParameterResultDto> parameterResults) {
    this.parameterResults = parameterResults;
  }  
}
