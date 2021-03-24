package com.tokyo.supermix.data.dto.report;

import java.sql.Date;
import java.util.List;

public class InComingSampleSummaryDto {
  private String testName;
  private String status;
  private String comment;
  private Double average;
  private Date date;
  private List<AcceptedValueDto> acceptanceCriteria;
  private List<ResultsDto> resultsDto;

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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
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

  public List<AcceptedValueDto> getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(List<AcceptedValueDto> acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public List<ResultsDto> getResultsDto() {
    return resultsDto;
  }

  public void setResultsDto(List<ResultsDto> resultsDto) {
    this.resultsDto = resultsDto;
  }
}
