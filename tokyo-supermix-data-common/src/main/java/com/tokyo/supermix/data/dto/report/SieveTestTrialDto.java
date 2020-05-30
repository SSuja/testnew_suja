package com.tokyo.supermix.data.dto.report;

public class SieveTestTrialDto {
  private Double percentageRetained;
  private Double cumulativeRetained;
  private Double passing;
  private Double Size;
  private Long min;
  private Long max;

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

  public Double getSize() {
    return Size;
  }

  public void setSize(Double size) {
    Size = size;
  }

  public Long getMin() {
    return min;
  }

  public void setMin(Long min) {
    this.min = min;
  }

  public Long getMax() {
    return max;
  }

  public void setMax(Long max) {
    this.max = max;
  }
}
