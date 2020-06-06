package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class PlantEquipmentCalibrationResponseDto {
  private Long id;
  private Date calibratedDate;
  private Date dueDate;
  private String calibrationType;
  private Long userId;
  private String userUsername;
  private String description;
  private String plantEquipmentSerialNo;
  private String status;
  private Long supplierId;
  private String supplierName;
  private String plantEquipmentEquipmentName;
  private Long employeeId;
  private String employeeName;
  private String accuracy;
  private Long noOfDays;

  public Long getNoOfDays() {
    return noOfDays;
  }

  public void setNoOfDays(Long noOfDays) {
    this.noOfDays = noOfDays;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String getplantEquipmentEquipmentName() {
    return plantEquipmentEquipmentName;
  }

  public void setplantEquipmentEquipmentName(String plantEquipmentEquipmentName) {
    this.plantEquipmentEquipmentName = plantEquipmentEquipmentName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCalibratedDate() {
    return calibratedDate;
  }

  public void setCalibratedDate(Date calibratedDate) {
    this.calibratedDate = calibratedDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getcalibrationType() {
    return calibrationType;
  }

  public void setcalibrationType(String calibrationType) {
    this.calibrationType = calibrationType;
  }

  public Long getuserId() {
    return userId;
  }

  public void setuserId(Long userId) {
    this.userId = userId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPlantEquipmentSerialNo() {
    return plantEquipmentSerialNo;
  }

  public void setPlantEquipmentSerialNo(String plantEquipmentSerialNo) {
    this.plantEquipmentSerialNo = plantEquipmentSerialNo;
  }

  public Long getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

  public String getUserUsername() {
    return userUsername;
  }

  public void setUserUsername(String userUsername) {
    this.userUsername = userUsername;
  }

  public String getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(String accuracy) {
    this.accuracy = accuracy;
  }

}
