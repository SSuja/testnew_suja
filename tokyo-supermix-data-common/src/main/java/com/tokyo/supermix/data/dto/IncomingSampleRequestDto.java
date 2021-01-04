package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;

public class IncomingSampleRequestDto {
  private String code;
  private String vehicleNo;
  private Status status;
  private Long rawMaterialId;
  @NotNull(message = "{incomingSampleRequestDto.plantCode.null}")
  @NotEmpty(message = "{incomingSampleRequestDto.plantCode.empty}")
  private String plantCode;
  private Long supplierId;
  private RawMaterialSampleType rawMaterialSampleType;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getVehicleNo() {
    return vehicleNo;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public Long getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  public RawMaterialSampleType getRawMaterialSampleType() {
    return rawMaterialSampleType;
  }

  public void setRawMaterialSampleType(RawMaterialSampleType rawMaterialSampleType) {
    this.rawMaterialSampleType = rawMaterialSampleType;
  }
}
