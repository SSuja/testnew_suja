package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Status;

public class FinishProductSampleRequestDto {
  private String code;
  @NotNull(message = "{finishProductSampleRequestDto.finishProductCode.null}")
  private String finishProductCode;
  @NotNull(message = "{finishProductSampleRequestDto.equipmentId.null}")
  private Long equipmentId;
  @NotNull(message = "{finishProductSampleRequestDto.mixDesignCode.null}")
  @NotEmpty(message = "{finishProductSampleRequestDto.mixDesignCode.empty}")
  private String mixDesignCode;
  private Status status;
  private Date date;
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
  public Long getEquipmentId() {
    return equipmentId;
  }
  public void setEquipmentId(Long equipmentId) {
    this.equipmentId = equipmentId;
  }
  public String getMixDesignCode() {
    return mixDesignCode;
  }
  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
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
}
