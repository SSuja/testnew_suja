package com.tokyo.supermix.data.dto.report;

import java.util.Date;
import com.tokyo.supermix.data.enums.Status;

public class MaterialTestReportDto {
  private String code;
  private Double average;
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

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public String getCommment() {
    return commment;
  }

  public void setCommment(String commment) {
    this.commment = commment;
  }
}
