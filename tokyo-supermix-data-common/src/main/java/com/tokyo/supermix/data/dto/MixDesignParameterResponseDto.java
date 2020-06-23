package com.tokyo.supermix.data.dto;

public class MixDesignParameterResponseDto {
  private Long id;
  private RawMaterialResponseDto rawMaterial;
  private MixDesignParameterResponseDto mixDesignParameter;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RawMaterialResponseDto getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public MixDesignParameterResponseDto getMixDesignParameter() {
    return mixDesignParameter;
  }

  public void setMixDesignParameter(MixDesignParameterResponseDto mixDesignParameter) {
    this.mixDesignParameter = mixDesignParameter;
  }
}
