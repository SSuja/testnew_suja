package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;

public class FinishProductSampleResponseDto {
  private String code;
  private String finishProductCode;
  private EquipmentDto equipment;
  private MixDesignResponseDto mixDesign;
  private Status status;
  private Date date;
  private String createdAt;
  private String updatedAt;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getFinishProductCode() {
    return finishProductCode;
  }

  public void setFinishProductCode(String finishProductCode) {
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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
