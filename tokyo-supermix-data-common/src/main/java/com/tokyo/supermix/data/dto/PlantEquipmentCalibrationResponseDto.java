package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.Supplier;
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
  private Supplier supplier;
  private Employee employee;
  private PlantEquipmentResponseDto plantEquipment;
  public Long getId() {
    return id;
  }
  public Employee getEmployee() {
    return employee;
  }
  public void setEmployee(Employee employee) {
    this.employee = employee;
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
  public Supplier getSupplier() {
    return supplier;
  }
  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }
  public PlantEquipmentResponseDto getPlantEquipment() {
    return plantEquipment;
  }
  public void setPlantEquipment(PlantEquipmentResponseDto plantEquipment) {
    this.plantEquipment = plantEquipment;
  }
 
}
