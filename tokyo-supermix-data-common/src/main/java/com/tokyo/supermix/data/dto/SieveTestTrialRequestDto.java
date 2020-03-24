package com.tokyo.supermix.data.dto;

public class SieveTestTrialRequestDto {
  private Long id;
  private Double cummalativeRetained;
  private Long sieveTestId;
  private Long sieveSizeId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getCummalativeRetained() {
    return cummalativeRetained;
  }

  public void setCummalativeRetained(Double cummalativeRetained) {
    this.cummalativeRetained = cummalativeRetained;
  }

  public Long getSieveTestId() {
    return sieveTestId;
  }

  public void setSieveTestId(Long sieveTestId) {
    this.sieveTestId = sieveTestId;
  }

  public Long getSieveSizeId() {
    return sieveSizeId;
  }

  public void setSieveSizeId(Long sieveSizeId) {
    this.sieveSizeId = sieveSizeId;
  }

}
