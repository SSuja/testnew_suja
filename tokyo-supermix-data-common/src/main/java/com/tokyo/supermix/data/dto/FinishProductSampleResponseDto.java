package com.tokyo.supermix.data.dto;

public class FinishProductSampleResponseDto {
  private Long id;
  private String workOrderNo;
  private Long finishProductCode;
  private ConcreteMixerResponseDto concreteMixer;
  private MixDesignResponseDto mixDesign;

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

  public ConcreteMixerResponseDto getConcreteMixer() {
    return concreteMixer;
  }

  public void setConcreteMixer(ConcreteMixerResponseDto concreteMixer) {
    this.concreteMixer = concreteMixer;
  }

  public MixDesignResponseDto getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesignResponseDto mixDesign) {
    this.mixDesign = mixDesign;
  }


}
