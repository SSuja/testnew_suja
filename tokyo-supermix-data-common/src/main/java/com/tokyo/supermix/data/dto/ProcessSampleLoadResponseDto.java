package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class ProcessSampleLoadResponseDto {
  private Long id;
  private String vehicleNo;
  private Long quantity;
  private Date date;
  private Date expiryDate;
  private String unit;
  private Long unitId;
  private ProcessSampleResponseDto processSample;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getVehicleNo() {
    return vehicleNo;
  }
  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }
  public String getUnit() {
    return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }
  public Long getUnitId() {
    return unitId;
  }
  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }
  public Long getQuantity() {
    return quantity;
  }
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public Date getExpiryDate() {
    return expiryDate;
  }
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
  public ProcessSampleResponseDto getProcessSample() {
    return processSample;
  }
  public void setProcessSample(ProcessSampleResponseDto processSample) {
    this.processSample = processSample;
  }
  
}
