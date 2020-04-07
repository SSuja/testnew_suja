package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.Project;

public class FinishProductSampleIssueResponseDto {
  private Long id;
  private String truckNo;
  private FinishProductSample finishProductSample;
  private Project project;

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

  public FinishProductSample getFinishProductSample() {
    return finishProductSample;
  }

  public void setFinishProductSample(FinishProductSample finishProductSample) {
    this.finishProductSample = finishProductSample;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }



}
