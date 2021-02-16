package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.FinishProductSampleType;
import com.tokyo.supermix.data.enums.Status;

public class FinishProductSampleResponseDto {
  private String code;
  private String finishProductCode;
  private PlantEquipmentResponseDto plantEquipment;
  private MixDesignResponseDto mixDesign;
  private Status status;
  private Date date;
  private String createdAt;
  private String updatedAt;
  private String truckNo;
  private ProjectResponseDto project;
  private PourDtoResponse pour;
  private String workOrderNumber;
  private FinishProductSampleType finishProductSampleType;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getFinishProductCode() {
    return finishProductCode;
  }

  public void setFinishProductCode(String finishProductCode) {
    this.finishProductCode = finishProductCode;
  }

  public PlantEquipmentResponseDto getPlantEquipment() {
    return plantEquipment;
  }

  public void setPlantEquipment(PlantEquipmentResponseDto plantEquipment) {
    this.plantEquipment = plantEquipment;
  }

  public MixDesignResponseDto getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesignResponseDto mixDesign) {
    this.mixDesign = mixDesign;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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

  public String getTruckNo() {
    return truckNo;
  }

  public void setTruckNo(String truckNo) {
    this.truckNo = truckNo;
  }

  public ProjectResponseDto getProject() {
    return project;
  }

  public void setProject(ProjectResponseDto project) {
    this.project = project;
  }

  public PourDtoResponse getPour() {
    return pour;
  }

  public void setPour(PourDtoResponse pour) {
    this.pour = pour;
  }

  public String getWorkOrderNumber() {
    return workOrderNumber;
  }

  public void setWorkOrderNumber(String workOrderNumber) {
    this.workOrderNumber = workOrderNumber;
  }

  public FinishProductSampleType getFinishProductSampleType() {
    return finishProductSampleType;
  }

  public void setFinishProductSampleType(FinishProductSampleType finishProductSampleType) {
    this.finishProductSampleType = finishProductSampleType;
  }
}
