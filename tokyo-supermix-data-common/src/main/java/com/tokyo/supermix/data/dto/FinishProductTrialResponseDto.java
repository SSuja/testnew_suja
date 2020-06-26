package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class FinishProductTrialResponseDto {
  private String code;
  private Long trialNo;
  private String testSample;
  private Long testSampleNo;
  private Date date;
  private Double value;
  private FinishProductTestResponseDto finishProductTest;
  private TestParameterResponseDto testParameter;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public String getTestSample() {
    return testSample;
  }

  public void setTestSample(String testSample) {
    this.testSample = testSample;
  }

  public Long getTestSampleNo() {
    return testSampleNo;
  }

  public void setTestSampleNo(Long testSampleNo) {
    this.testSampleNo = testSampleNo;
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
