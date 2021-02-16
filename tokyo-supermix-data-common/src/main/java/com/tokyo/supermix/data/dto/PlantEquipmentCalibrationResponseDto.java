package com.tokyo.supermix.data.dto;

import java.sql.Date;

import com.tokyo.supermix.data.enums.CalibrationType;
import com.tokyo.supermix.data.enums.Status;

public class PlantEquipmentCalibrationResponseDto {
  private Long id;
  private Date calibratedDate;
  private Date dueDate;
  private CalibrationType calibrationType;
  private String description;
  private Status status;
  private String accuracy;
  private Long noOfDays;
  private SupplierResponseDto supplier;
  private EmployeeResponseDto employee;
  private PlantEquipmentResponseDto plantEquipment;
  private boolean sentMail;

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

  public CalibrationType getCalibrationType() {
    return calibrationType;
  }

  public void setCalibrationType(CalibrationType calibrationType) {
    this.calibrationType = calibrationType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(String accuracy) {
    this.accuracy = accuracy;
  }

  public Long getNoOfDays() {
    return noOfDays;
  }

  public void setNoOfDays(Long noOfDays) {
    this.noOfDays = noOfDays;
  }

  public SupplierResponseDto getSupplier() {
    return supplier;
  }

  public void setSupplier(SupplierResponseDto supplier) {
    this.supplier = supplier;
  }

  public EmployeeResponseDto getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeResponseDto employee) {
    this.employee = employee;
  }

  public PlantEquipmentResponseDto getPlantEquipment() {
    return plantEquipment;
  }

  public void setPlantEquipment(PlantEquipmentResponseDto plantEquipment) {
    this.plantEquipment = plantEquipment;
  }

  public boolean isSentMail() {
    return sentMail;
  }

  public void setSentMail(boolean sentMail) {
    this.sentMail = sentMail;
  }

}
