package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.dto.report.AcceptedValueDto;

public class IncomingSampleJasperTestDto {
  private String testName;
  private String status;
  private Double average;
  private Date date;
  private AcceptedValueDto acceptanceCriteria;

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public AcceptedValueDto getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(AcceptedValueDto acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

}
