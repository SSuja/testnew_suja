package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Status;

public class FinishProductSampleRequestDto {
  private String code;
  private String workOrderNo;
  @NotNull(message = "{finishProductSampleRequestDto.finishProductCode.null}")
  private String finishProductCode;
  @NotNull(message = "{finishProductSampleRequestDto.equipmentId.null}")
  private Long equipmentId;
  @NotNull(message = "{finishProductSampleRequestDto.mixDesignCode.null}")
  @NotEmpty(message = "{finishProductSampleRequestDto.mixDesignCode.empty}")
  private String mixDesignCode;
  private Status status;
  private String projectCode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getWorkOrderNo() {
    return workOrderNo;
  }

  public void setWorkOrderNo(String workOrderNo) {
    this.workOrderNo = workOrderNo;
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

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }
}
