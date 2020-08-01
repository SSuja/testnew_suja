package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.FinishProductState;

public class FinishProductTrialResponseDto {
  private Long id;
  private Long trialNo;
  private FinishProductState testSample;
  private Date date;
  private Double value;
  private FinishProductTestResponseDto finishProductTest;
  private TestParameterResponseDto testParameter;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public FinishProductState getTestSample() {
    return testSample;
  }

  public void setTestSample(FinishProductState testSample) {
    this.testSample = testSample;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public FinishProductTestResponseDto getFinishProductTest() {
    return finishProductTest;
  }

  public void setFinishProductTest(FinishProductTestResponseDto finishProductTest) {
    this.finishProductTest = finishProductTest;
  }

  public TestParameterResponseDto getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameterResponseDto testParameter) {
    this.testParameter = testParameter;
  }
}
