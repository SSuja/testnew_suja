package com.tokyo.supermix.data.dto;


public class SieveTestTrialRequestDto {
  private Long sieveSizeId;
  private Long sieveTestId;
  private Double cummalativeRetained;

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

  public Double getCummalativeRetained() {
    return cummalativeRetained;
  }

  public void setCummalativeRetained(Double cummalativeRetained) {
    this.cummalativeRetained = cummalativeRetained;
  }
}
