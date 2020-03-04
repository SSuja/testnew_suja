package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class ProcessSampleLoadResponseDto {
  private Long id;
  private String vehicleNo;
  private UnitDto unit;
  private Long quantity;
  private Date dateAndTime;
  private Date expiryDate;
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
  
  public UnitDto getUnit() {
    return unit;
  }
  public void setUnit(UnitDto unit) {
    this.unit = unit;
  }
  public Long getQuantity() {
    return quantity;
  }
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
  public Date getDateAndTime() {
    return dateAndTime;
  }
  public void setDateAndTime(Date dateAndTime) {
    this.dateAndTime = dateAndTime;
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
