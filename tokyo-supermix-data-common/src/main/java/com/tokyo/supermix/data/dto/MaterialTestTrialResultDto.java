package com.tokyo.supermix.data.dto;

import java.util.List;

public class MaterialTestTrialResultDto {
  private String trialNo;
  List<AbbrevationAndValueDto> abbrevationAndValues;

  public String getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(String trialNo) {
    this.trialNo = trialNo;
  }

  public List<AbbrevationAndValueDto> getAbbrevationAndValues() {
    return abbrevationAndValues;
  }

  public void setAbbrevationAndValues(List<AbbrevationAndValueDto> abbrevationAndValues) {
    this.abbrevationAndValues = abbrevationAndValues;
  }
}
