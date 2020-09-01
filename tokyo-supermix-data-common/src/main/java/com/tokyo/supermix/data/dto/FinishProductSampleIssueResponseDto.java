package com.tokyo.supermix.data.dto;

public class FinishProductSampleIssueResponseDto {
  private String code;
  private String truckNo;
  private ProjectResponseDto project;
  private PourDtoResponse pour;
  private String createdAt;
  private String updatedAt;
  private String workOrderNumber;
  private MixDesignResponseDto mixDesign;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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

  public String getWorkOrderNumber() {
    return workOrderNumber;
  }

  public void setWorkOrderNumber(String workOrderNumber) {
    this.workOrderNumber = workOrderNumber;
  }

  public MixDesignResponseDto getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesignResponseDto mixDesign) {
    this.mixDesign = mixDesign;
  }
}
