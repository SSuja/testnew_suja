package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlantEquipmentRequestDto {
  @NotNull(message = "{plantEquipmentDto.serialNo.null}")
  @NotEmpty(message = "{plantequipmentDto.serialNo.empty}")
  private String serialNo;
  @NotNull(message = "{plantEquipmentDto.brandName.null}")
  @NotEmpty(message = "{plantEquipmentDto.brandName.empty}")
  private String brandName;
  private String modelName;
  private String description;
  @NotNull(message = "{plantEquipmentDto.plantCode.null}")
  @NotEmpty(message = "{plantEquipmentDto.plantCode.empty}")
  private String plantCode;
  private Long equipmentId;
  private boolean calibrationExists;

  public String getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public Long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(Long equipmentId) {
    this.equipmentId = equipmentId;
  }

  public boolean isCalibrationExists() {
    return calibrationExists;
  }

  public void setCalibrationExists(boolean calibrationExists) {
    this.calibrationExists = calibrationExists;
  }
}
