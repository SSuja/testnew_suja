package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.entities.Employee;


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
  private Employee employee;

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
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


}
