package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class IncomingSampleResponseDto {
  private String code;
  private String vehicleNo;
  private Date date;
  private Boolean status;
  private RawMaterialResponseDto rawMaterial;
  private PlantDto plant;
  private SupplierResponseDto supplier;

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

  public RawMaterialResponseDto getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialResponseDto rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public SupplierResponseDto getSupplier() {
    return supplier;
  }

  public void setSupplier(SupplierResponseDto supplier) {
    this.supplier = supplier;
  }

}
