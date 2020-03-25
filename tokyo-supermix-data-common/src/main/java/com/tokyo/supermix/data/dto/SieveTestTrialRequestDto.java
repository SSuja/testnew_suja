package com.tokyo.supermix.data.dto;

public class SieveTestTrialRequestDto {
  private Long id;
  private Double cumulativeRetained;
  private String sieveTestCode;
  private Long sieveSizeId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getCumulativeRetained() {
    return cumulativeRetained;
  }

  public void setCumulativeRetained(Double cumulativeRetained) {
    this.cumulativeRetained = cumulativeRetained;
  }

  public String getSieveTestCode() {
    return sieveTestCode;
  }

  public void setSieveTestCode(String sieveTestCode) {
    this.sieveTestCode = sieveTestCode;
  }

  public Long getSieveSizeId() {
    return sieveSizeId;
  }

  public void setSieveSizeId(Long sieveSizeId) {
    this.sieveSizeId = sieveSizeId;
  }

}
