package com.tokyo.supermix.data.dto;

public class SieveTestTrialRequestDto {
  private Long id;
  private Double cummalativeRetained;
  private String sieveTestCode;
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
