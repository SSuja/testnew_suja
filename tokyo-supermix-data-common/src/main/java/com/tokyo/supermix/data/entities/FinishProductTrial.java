package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_trial")
public class FinishProductTrial implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Long trialNo;
  private String testSample;
  private Long testSampleNo;
  private Date date;
  private Double value;
  @ManyToOne
  @JoinColumn(name = "finishProductTestId", nullable = false)
  private FinishProductTest finishProductTest;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = true)
  private TestParameter testParameter;

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

  public FinishProductTest getFinishProductTest() {
    return finishProductTest;
  }

  public void setFinishProductTest(FinishProductTest finishProductTest) {
    this.finishProductTest = finishProductTest;
  }

  public TestParameter getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameter testParameter) {
    this.testParameter = testParameter;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
