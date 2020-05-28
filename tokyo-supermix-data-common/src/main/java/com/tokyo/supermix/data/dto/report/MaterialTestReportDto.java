package com.tokyo.supermix.data.dto.report;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;

public class MaterialTestReportDto {
  private String code;
  private Date date;
  private Double avarage;
  private Long noOfTrial;
  private Double average;
  private Status status;
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public Double getAvarage() {
    return avarage;
  }
  public void setAvarage(Double avarage) {
    this.avarage = avarage;
  }
  public Long getNoOfTrial() {
    return noOfTrial;
  }
  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }
  public Double getAverage() {
    return average;
  }
  public void setAverage(Double average) {
    this.average = average;
  }
  public Status getStatus() {
    return status;
  }
  public void setStatus(Status status) {
    this.status = status;
  }  
}
