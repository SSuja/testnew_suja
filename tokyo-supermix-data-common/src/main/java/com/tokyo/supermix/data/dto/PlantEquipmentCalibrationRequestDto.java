package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.CalibrationType;

public class PlantEquipmentCalibrationRequestDto {
  private Long id;
  @NotNull(message = "{PlantEquipmentCalibrationRequestDto.calibratedDate.null}")
  private Date calibratedDate;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.dueDate.null}")
  private Date dueDate;
  private CalibrationType calibrationType;
  private Long userId;
  private String description;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.plantEquipmentId.null}")
  private Long plantEquipmentId;
  private Long supplierId;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.status.null}")
  @NotEmpty(message = "{plantEquipmentCalibrationRequestDto.status.empty}")
  private String status;

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

  public Long getPlantEquipmentId() {
    return plantEquipmentId;
  }

  public void setPlantEquipmentId(Long plantEquipmentId) {
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
}
