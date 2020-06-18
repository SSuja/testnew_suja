package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.MaterialTest;

public class MaterialTestTrialResponseDto {
  private String code;
  private Long trialNo;
  private Double result;
  private MaterialTest materialTest;
  private String createdAt;
  private SieveSizeResponseDto sieveSize;

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public MaterialTest getMaterialTest() {
    return materialTest;
  }

  public void setMaterialTest(MaterialTest materialTest) {
    this.materialTest = materialTest;
  }

  public SieveSizeResponseDto getSieveSize() {
    return sieveSize;
  }

  public void setSieveSize(SieveSizeResponseDto sieveSize) {
    this.sieveSize = sieveSize;
  }
}
