package com.tokyo.supermix.data.dto;

public class FinishProductSampleIssueResponseDto {
  private Long id;
  private String truckNo;
  private FinishProductSampleResponseDto finishProductSample;
  private ProjectResponseDto project;
  private PourDtoResponse pour;
  private String createdAt;
  private String updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTruckNo() {
    return truckNo;
  }

  public void setTruckNo(String truckNo) {
    this.truckNo = truckNo;
  }

  public FinishProductSampleResponseDto getFinishProductSample() {
    return finishProductSample;
  }

  public void setFinishProductSample(FinishProductSampleResponseDto finishProductSample) {
    this.finishProductSample = finishProductSample;
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
}
