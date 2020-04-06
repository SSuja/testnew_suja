package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FinishProductSampleRequestDto {
  private Long id;
  private String workOrderNo;
  @NotNull(message = "{finishProductSampleRequestDto.finishProductCode.null}")
  private Long finishProductCode;
  @NotNull(message = "{finishProductSampleRequestDto.concreteMixerId.null}")
  private Long concreteMixerId;
  @NotNull(message = "{finishProductSampleRequestDto.mixDesignCode.null}")
  @NotEmpty(message = "{finishProductSampleRequestDto.mixDesignCode.empty}")
  private String mixDesignCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getWorkOrderNo() {
    return workOrderNo;
  }

  public void setWorkOrderNo(String workOrderNo) {
    this.workOrderNo = workOrderNo;
  }

  public Long getFinishProductCode() {
    return finishProductCode;
  }

  public void setFinishProductCode(Long finishProductCode) {
    this.finishProductCode = finishProductCode;
  }

  public Long getConcreteMixerId() {
    return concreteMixerId;
  }

  public void setConcreteMixerId(Long concreteMixerId) {
    this.concreteMixerId = concreteMixerId;
  }

  public String getMixDesignCode() {
    return mixDesignCode;
  }

  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
  }
}
