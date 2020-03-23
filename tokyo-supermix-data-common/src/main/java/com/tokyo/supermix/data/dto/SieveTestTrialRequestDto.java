package com.tokyo.supermix.data.dto;


public class SieveTestTrialRequestDto {

  private Long sieveSizeId;
  private Long sieveTestId;
  private Double retainedWeight;

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

  public Double getRetainedWeight() {
    return retainedWeight;
  }

  public void setRetainedWeight(Double retainedWeight) {
    this.retainedWeight = retainedWeight;
  }


}
