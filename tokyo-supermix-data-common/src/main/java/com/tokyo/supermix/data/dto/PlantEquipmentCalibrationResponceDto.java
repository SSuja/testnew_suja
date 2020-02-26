package com.tokyo.supermix.data.dto;

import java.sql.Date;


public class PlantEquipmentCalibrationResponceDto {
  private Long id;
  private Date calibratedDate;
  private Date dueDate;
  private String calibrationType;
  private Long userId;
  private String userUsername;
  private String description;
  private Long plantEquipmentId;
  private Long supplierId;
  private String status;
  private String supplierName;
  private String plantEquipmentEquipmentName;
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
  public Long getplantEquipmentId() {
    return plantEquipmentId;
  }
  public void setplantEquipmentId(Long plantEquipmentId) {
    this.plantEquipmentId = plantEquipmentId;
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
 

}
