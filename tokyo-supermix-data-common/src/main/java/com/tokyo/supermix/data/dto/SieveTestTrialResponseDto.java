package com.tokyo.supermix.data.dto;

public class SieveTestTrialResponseDto {
  private Long id;
  private SieveSizeResponseDto sieveSize;
  private SieveTestResponseDto sieveTest;
  private Double percentageRetained;
  private Double cumulativeRetained;
  private Double passing;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SieveSizeResponseDto getSieveSize() {
    return sieveSize;
  }

  public void setSieveSize(SieveSizeResponseDto sieveSize) {
    this.sieveSize = sieveSize;
  }

  public SieveTestResponseDto getSieveTest() {
    return sieveTest;
  }

  public void setSieveTest(SieveTestResponseDto sieveTest) {
    this.sieveTest = sieveTest;
  }

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
