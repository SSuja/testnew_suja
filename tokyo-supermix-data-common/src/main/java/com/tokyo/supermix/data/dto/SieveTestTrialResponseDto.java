package com.tokyo.supermix.data.dto;

public class SieveTestTrialResponseDto {
  private Long id;
  private Long sieveSizeId;
  private Long sieveTestId;
  private Double percentageRetained;
  private Double cummalativeRetained;
  private Double passing;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSieveSizeId() {
    return sieveSizeId;
  }

  public void setSieveSizeId(Long sieveSizeId) {
    this.sieveSizeId = sieveSizeId;
  }

  public Long getSieveTestId() {
    return sieveTestId;
  }

  public void setSieveTestId(Long sieveTestId) {
    this.sieveTestId = sieveTestId;
  }

  public Double getPercentageRetained() {
    return percentageRetained;
  }

  public void setPercentageRetained(Double percentageRetained) {
    this.percentageRetained = percentageRetained;
  }

  public Double getCummalativeRetained() {
    return cummalativeRetained;
  }

  public void setCummalativeRetained(Double cummalativeRetained) {
    this.cummalativeRetained = cummalativeRetained;
  }

  public Double getPassing() {
    return passing;
  }

  public void setPassing(Double passing) {
    this.passing = passing;
  }

}
