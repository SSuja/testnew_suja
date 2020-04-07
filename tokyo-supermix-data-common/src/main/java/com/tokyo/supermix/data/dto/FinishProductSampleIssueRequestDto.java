package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FinishProductSampleIssueRequestDto {
  private Long id;
  @NotEmpty(message = "{finishProductSampleIssueRequestDto.truckNo.empty}")
  @NotNull(message = "{finishProductSampleIssueRequestDto.truckNo.null}")
  private String truckNo;
  private Long finishProductSampleId;
  private String projectCode;

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

  public Long getFinishProductSampleId() {
    return finishProductSampleId;
  }

  public void setFinishProductSampleId(Long finishProductSampleId) {
    this.finishProductSampleId = finishProductSampleId;
  }

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }



}
