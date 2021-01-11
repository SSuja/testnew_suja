package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;

public class IncomingSampleResponseDto {
  private String code;
  private String vehicleNo;
  private String createdAt;
  private String updatedAt;
  private Status status;
  private RawMaterialResponseDto rawMaterial;
  private PlantDto plant;
  private SupplierResponseDto supplier;
  private RawMaterialSampleType rawMaterialSampleType;
  private Date date;

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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
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

  public RawMaterialSampleType getRawMaterialSampleType() {
    return rawMaterialSampleType;
  }

  public void setRawMaterialSampleType(RawMaterialSampleType rawMaterialSampleType) {
    this.rawMaterialSampleType = rawMaterialSampleType;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
