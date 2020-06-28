package com.tokyo.supermix.data.dto.report;

public class SieveSizeDto {
  private Long min;
  private Long max;
  private Double sieveSize;

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

  public Double getSieveSize() {
    return sieveSize;
  }

  public void setSieveSize(Double sieveSize) {
    this.sieveSize = sieveSize;
  }
}
