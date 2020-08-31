package com.tokyo.supermix.data.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Status;

public class MixDesignRequestDto {
  private String code;
  @NotNull(message = "{mixDesignRequestDto.date.null}")
  private Date date;
  private String plantCode;
  private Status status;
  private Long rawMaterialId;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }
}
