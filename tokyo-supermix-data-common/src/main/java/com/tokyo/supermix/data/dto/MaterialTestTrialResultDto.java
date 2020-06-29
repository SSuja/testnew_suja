package com.tokyo.supermix.data.dto;

import java.util.List;

public class MaterialTestTrialResultDto {
  private String abbrevation;
  private List<AbbrevationAndValueDto> abbrevationAndValues;
  public String getAbbrevation() {
    return abbrevation;
  }
  public void setAbbrevation(String abbrevation) {
    this.abbrevation = abbrevation;
  }
  public List<AbbrevationAndValueDto> getAbbrevationAndValues() {
    return abbrevationAndValues;
  }
  public void setAbbrevationAndValues(List<AbbrevationAndValueDto> abbrevationAndValues) {
    this.abbrevationAndValues = abbrevationAndValues;
  }
}
