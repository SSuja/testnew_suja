package com.tokyo.supermix.data.dto;

import java.util.List;

public class ParameterResultViewResponseDto {
  private String materialTestTrialCode;
  private Long materialTestTrialTrialNo;
  private List<ParameterResultDetails> parameterResult;

  public List<ParameterResultDetails> getParameterResult() {
    return parameterResult;
  }

  public void setParameterResult(List<ParameterResultDetails> parameterResult) {
    this.parameterResult = parameterResult;
  }

  public String getMaterialTestTrialCode() {
    return materialTestTrialCode;
  }

  public void setMaterialTestTrialCode(String materialTestTrialCode) {
    this.materialTestTrialCode = materialTestTrialCode;
  }

  public Long getMaterialTestTrialTrialNo() {
    return materialTestTrialTrialNo;
  }

  public void setMaterialTestTrialTrialNo(Long materialTestTrialTrialNo) {
    this.materialTestTrialTrialNo = materialTestTrialTrialNo;
  }
}
