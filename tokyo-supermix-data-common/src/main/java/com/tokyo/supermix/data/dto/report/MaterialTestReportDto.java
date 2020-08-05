package com.tokyo.supermix.data.dto.report;

import java.util.Date;
import java.util.List;
import com.tokyo.supermix.data.enums.Status;

public class MaterialTestReportDto {
  private String code;
  private List<MaterialResult> materialResults;
  private Long noOfTrial;
  private Status status;
  private Date date;
  private String commment;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
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

  public List<MaterialResult> getMaterialResults() {
    return materialResults;
  }

  public void setMaterialResults(List<MaterialResult> materialResults) {
    this.materialResults = materialResults;
  }

  public String getCommment() {
    return commment;
  }

  public void setCommment(String commment) {
    this.commment = commment;
  }
}
