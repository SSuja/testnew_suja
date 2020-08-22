package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class IncomingSampleJasperTestDto {
  private String testName;
  private String status;
  private Double average;
  private Date date;
  private AcceptedValueJasperDto acceptanceCriteria;
  
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

<<<<<<< HEAD
  public AcceptedValueDto getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(AcceptedValueDto acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

}
=======
  public AcceptedValueJasperDto getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(AcceptedValueJasperDto acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  
}
>>>>>>> 53bca6f8aa899dae5c483e0d42f0694262370345
