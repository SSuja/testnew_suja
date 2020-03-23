package com.tokyo.supermix.data.dto;

public class SieveAcceptedValueRequestDto {
  private Long id;
  private Long min;
  private Long max;
  private Long sieveSizeId;
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
  public Long getSieveSizeId() {
    return sieveSizeId;
  }
  public void setSieveSizeId(Long sieveSizeId) {
    this.sieveSizeId = sieveSizeId;
  }
  
}
