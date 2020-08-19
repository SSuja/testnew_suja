package com.tokyo.supermix.data.dto.report;

import java.sql.Date;
import java.util.List;

public class IncomingSampleTestDto {
  private String testName;
  private String status;
  private String comment;
  private Double average;
  private Date date;
  private List<AcceptedValueDto> acceptanceCriteria;

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

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

  public List<AcceptedValueDto> getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(List<AcceptedValueDto> acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
