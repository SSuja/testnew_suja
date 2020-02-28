package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotNull;

public class IncomingSampleRequestDto {
  @NotNull(message = "{incomingSampleRequestDto.code.null}")
  @NotNull(message = "{incomingSampleRequestDto.code.empty}")
  private String code;
  private String vehicleNo;
  @NotNull(message = "{incomingSampleRequestDto.date.null}")
  private Date date;
  private Boolean status = false;
  private Long rawMaterialId;
  @NotNull(message = "{incomingSampleRequestDto.plantCode.null}")
  @NotNull(message = "{incomingSampleRequestDto.plantCode.empty}")
  private String plantCode;
  private Long supplierId;

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
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
}
