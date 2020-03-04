package com.tokyo.supermix.data.dto;
import java.sql.Date;

public class ProcessSampleLoadRequestDto {
  private Long id;
  private String vehicleNo;
  private Long unitId;
  private Long quantity;
  private Date dateAndTime;
  private Date expiryDate;
  private String processSampleCode;
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
  public String getProcessSampleCode() {
    return processSampleCode;
  }
  public void setProcessSampleCode(String processSampleCode) {
    this.processSampleCode = processSampleCode;
  }
}
