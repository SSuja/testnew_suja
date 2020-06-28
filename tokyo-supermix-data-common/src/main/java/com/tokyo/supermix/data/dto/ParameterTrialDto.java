package com.tokyo.supermix.data.dto;

import java.util.List;

public class ParameterTrialDto {
  private String materialTestCode;
  private List<TrialDto> trialDtos;

  public String getMaterialTestCode() {
    return materialTestCode;
  }

  public void setMaterialTestCode(String materialTestCode) {
    this.materialTestCode = materialTestCode;
  }

  public List<TrialDto> getTrialDtos() {
    return trialDtos;
  }

  public void setTrialDtos(List<TrialDto> trialDtos) {
    this.trialDtos = trialDtos;
  }
}
