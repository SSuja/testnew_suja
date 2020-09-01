package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FinishProductSampleIssueRequestDto {
  private String code;
  @NotEmpty(message = "{finishProductSampleIssueRequestDto.truckNo.empty}")
  @NotNull(message = "{finishProductSampleIssueRequestDto.truckNo.null}")
  private String truckNo;
  private ProjectRequestDto project;
  private Long pourId;
  private String workOrderNumber;

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

  public ProjectRequestDto getProject() {
    return project;
  }

  public void setProject(ProjectRequestDto project) {
    this.project = project;
  }

  public Long getPourId() {
    return pourId;
  }

  public void setPourId(Long pourId) {
    this.pourId = pourId;
  }

  public String getWorkOrderNumber() {
    return workOrderNumber;
  }

  public void setWorkOrderNumber(String workOrderNumber) {
    this.workOrderNumber = workOrderNumber;
  }
}
