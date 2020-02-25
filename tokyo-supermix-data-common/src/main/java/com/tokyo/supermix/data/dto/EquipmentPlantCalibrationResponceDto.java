package com.tokyo.supermix.data.dto;

import java.sql.Date;


public class EquipmentPlantCalibrationResponceDto {
  private Long id;
  private Date calibratedDate;
  private Date dueDate;
  private String calibratedBy;
  private String testerName;
  private String description;
  private Long equipmentPlantId;
  private Long supplierId;
  private String status;
  private String supplierName;
  private String equipmentPlantEquipmentName;
  public String getEquipmentPlantEquipmentName() {
    return equipmentPlantEquipmentName;
  }
  public void setEquipmentPlantEquipmentName(String equipmentPlantEquipmentName) {
    this.equipmentPlantEquipmentName = equipmentPlantEquipmentName;
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
  public String getCalibratedBy() {
    return calibratedBy;
  }
  public void setCalibratedBy(String calibratedBy) {
    this.calibratedBy = calibratedBy;
  }
  public String getTesterName() {
    return testerName;
  }
  public void setTesterName(String testerName) {
    this.testerName = testerName;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Long getEquipmentPlantId() {
    return equipmentPlantId;
  }
  public void setEquipmentPlantId(Long equipmentPlantId) {
    this.equipmentPlantId = equipmentPlantId;
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
 

}
