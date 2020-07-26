package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class SieveTestTrialDto {
  private String size;
  private List<SieveResultAndParameter> sieveResultAndParameter;

  public List<SieveResultAndParameter> getSieveResultAndParameter() {
    return sieveResultAndParameter;
  }

  public void setSieveResultAndParameter(List<SieveResultAndParameter> sieveResultAndParameter) {
    this.sieveResultAndParameter = sieveResultAndParameter;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }
}
