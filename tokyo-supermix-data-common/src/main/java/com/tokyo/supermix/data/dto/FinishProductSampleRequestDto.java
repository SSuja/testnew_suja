package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.FinishProductSampleType;
import com.tokyo.supermix.data.enums.Status;

public class FinishProductSampleRequestDto {
  private String code;
  private String finishProductCode;
  private String plantEquipmentSerialNo;
  @NotNull(message = "{finishProductSampleRequestDto.mixDesignCode.null}")
  @NotEmpty(message = "{finishProductSampleRequestDto.mixDesignCode.empty}")
  private String mixDesignCode;
  private Status status;
  private Date date;
  private String truckNo;
  private String projectCode;
  private Long pourId;
  private String workOrderNumber;
  private Long userId;
  private boolean sentMail;
  private FinishProductSampleType finishProductSampleType;

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

  public String getPlantEquipmentSerialNo() {
    return plantEquipmentSerialNo;
  }

  public void setPlantEquipmentSerialNo(String plantEquipmentSerialNo) {
    this.plantEquipmentSerialNo = plantEquipmentSerialNo;
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

  public String getTruckNo() {
    return truckNo;
  }

  public void setTruckNo(String truckNo) {
    this.truckNo = truckNo;
  }

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }

  public Long getPourId() {
    return pourId;
  }

  public void setPourId(Long pourId) {
    this.pourId = pourId;
  }

  public String getWorkOrderNumber() {
    return workOrderNumber;
  }

  public void setWorkOrderNumber(String workOrderNumber) {
    this.workOrderNumber = workOrderNumber;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public boolean isSentMail() {
    return sentMail;
  }

  public void setSentMail(boolean sentMail) {
    this.sentMail = sentMail;
  }

  public FinishProductSampleType getFinishProductSampleType() {
    return finishProductSampleType;
  }

  public void setFinishProductSampleType(FinishProductSampleType finishProductSampleType) {
    this.finishProductSampleType = finishProductSampleType;
  }
}
