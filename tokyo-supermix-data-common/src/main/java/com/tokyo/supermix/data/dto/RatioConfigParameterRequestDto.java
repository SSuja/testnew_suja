package com.tokyo.supermix.data.dto;

public class RatioConfigParameterRequestDto {

  private Long id;
  private Long ratioConfigId;
  private Long rawMaterialId;
  private String abbreviation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRatioConfigId() {
    return ratioConfigId;
  }

  public void setRatioConfigId(Long ratioConfigId) {
    this.ratioConfigId = ratioConfigId;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
}
