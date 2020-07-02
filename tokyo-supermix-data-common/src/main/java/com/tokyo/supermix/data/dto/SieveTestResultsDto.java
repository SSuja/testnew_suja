package com.tokyo.supermix.data.dto;

import java.util.List;

public class SieveTestResultsDto {
  private double sieveSize;
  private List<SieveParameterResultDto> SieveParameters;
  
  public double getSieveSize() {
    return sieveSize;
  }
  public void setSieveSize(double sieveSize) {
    this.sieveSize = sieveSize;
  }
  public List<SieveParameterResultDto> getSieveParameters() {
    return SieveParameters;
  }
  public void setSieveParameters(List<SieveParameterResultDto> sieveParameters) {
    SieveParameters = sieveParameters;
  }
  
}
