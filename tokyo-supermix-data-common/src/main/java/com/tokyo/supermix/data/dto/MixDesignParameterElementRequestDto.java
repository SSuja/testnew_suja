package com.tokyo.supermix.data.dto;

public class MixDesignParameterElementRequestDto {
  private Long id;
  private Long rawMaterialId;
  private Long mixDesignParameterId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public Long getMixDesignParameterId() {
    return mixDesignParameterId;
  }

  public void setMixDesignParameterId(Long mixDesignParameterId) {
    this.mixDesignParameterId = mixDesignParameterId;
  }
}
