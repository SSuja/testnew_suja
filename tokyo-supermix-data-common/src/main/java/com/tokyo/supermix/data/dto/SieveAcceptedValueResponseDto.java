package com.tokyo.supermix.data.dto;

public class SieveAcceptedValueResponseDto {
  private Long id;
  private Long min;
  private Long max;
  private SieveSizeResponseDto sieveSize;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public SieveSizeResponseDto getSieveSize() {
    return sieveSize;
  }

  public void setSieveSize(SieveSizeResponseDto sieveSize) {
    this.sieveSize = sieveSize;
  }

}
