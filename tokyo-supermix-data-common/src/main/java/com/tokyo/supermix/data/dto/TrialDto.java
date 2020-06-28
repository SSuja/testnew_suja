package com.tokyo.supermix.data.dto;

import java.util.List;

public class TrialDto {
  private Long trialNo;
  private List<ParameterResultValueDto> parameterResultValueDtos;

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public List<ParameterResultValueDto> getParameterResultValueDtos() {
    return parameterResultValueDtos;
  }

  public void setParameterResultValueDtos(List<ParameterResultValueDto> parameterResultValueDtos) {
    this.parameterResultValueDtos = parameterResultValueDtos;
  }
}
