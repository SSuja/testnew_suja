package com.tokyo.supermix.data.dto.report;

public class SieveTestTrialDto {
  private Double percentageRetained;
  private Double cumulativeRetained;
  private Double passing;
  

  public Double getPercentageRetained() {
    return percentageRetained;
  }

  public void setPercentageRetained(Double percentageRetained) {
    this.percentageRetained = percentageRetained;
  }

  public Double getCumulativeRetained() {
    return cumulativeRetained;
  }

  public void setCumulativeRetained(Double cumulativeRetained) {
    this.cumulativeRetained = cumulativeRetained;
  }

  public Double getPassing() {
    return passing;
  }

  public void setPassing(Double passing) {
    this.passing = passing;
  }

}
