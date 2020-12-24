package com.tokyo.supermix.data.dto;

public class MixDesignRatioConfigRequestDto {

  private Long id;
  private String mixDesignCode;
  private Long ratioConfigId;
  private Double value;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMixDesignCode() {
    return mixDesignCode;
  }

  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
  }

  public Long getRatioConfigId() {
    return ratioConfigId;
  }

  public void setRatioConfigId(Long ratioConfigId) {
    this.ratioConfigId = ratioConfigId;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }
}
