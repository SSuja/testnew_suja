package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;

public class FinishProductSampleResponseDto {
  private Long id;
  private String workOrderNo;
  private Long finishProductCode;
  private EquipmentDto equipment;
  private MixDesignResponseDto mixDesign;
  private Status status;
  private String createdAt;
  private String updatedAt;

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

  public EquipmentDto getEquipment() {
    return equipment;
  }

  public void setEquipment(EquipmentDto equipment) {
    this.equipment = equipment;
  }

  public MixDesignResponseDto getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesignResponseDto mixDesign) {
    this.mixDesign = mixDesign;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
