package com.tokyo.supermix.data.dto;

import java.time.Instant;
import com.tokyo.supermix.data.enums.Status;

public class IncomingSampleResponseDto {
  private String code;
  private String vehicleNo;
  private Instant createdAt;
  private Instant updatedAt;
  private Status status;
  private RawMaterialResponseDto rawMaterial;
  private PlantDto plant;
  private SupplierResponseDto supplier;

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

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
