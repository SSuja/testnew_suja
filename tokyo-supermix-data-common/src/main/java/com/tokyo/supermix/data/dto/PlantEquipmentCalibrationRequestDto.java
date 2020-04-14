package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.CalibrationType;

public class PlantEquipmentCalibrationRequestDto {
  private Long id;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.calibratedDate.null}")
  private Date calibratedDate;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.dueDate.null}")
  private Date dueDate;
  private CalibrationType calibrationType;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.userId.null}")
  private Long userId;
  private String description;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.plantEquipmentSerialNo.null}")
  @NotEmpty(message = "{plantEquipmentCalibrationRequestDto.plantEquipmentSerialNo.empty}")
  private String plantEquipmentSerialNo;
  private Long supplierId;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.status.null}")
  @NotEmpty(message = "{plantEquipmentCalibrationRequestDto.status.empty}")
  private String status;
  private Long employeeId;
  private Long supplierCategoryId;

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
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

  public CalibrationType getCalibrationType() {
    return calibrationType;
  }

  public void setCalibrationType(CalibrationType calibrationType) {
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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getSupplierCategoryId() {
    return supplierCategoryId;
  }

  public void setSupplierCategoryId(Long supplierCategoryId) {
    this.supplierCategoryId = supplierCategoryId;
  }

}
