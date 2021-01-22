package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_trial")
public class FinishProductTrial extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long trialNo;
  private Long testSampleNo;
  private Date date;
  private Double value;
  @ManyToOne
  @JoinColumn(name = "finishProductTestCode", nullable = false)
  private FinishProductTest finishProductTest;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = true)
  private TestParameter testParameter;
  private String DateValue;

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
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

  public Long getTestSampleNo() {
    return testSampleNo;
  }

  public void setTestSampleNo(Long testSampleNo) {
    this.testSampleNo = testSampleNo;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDateValue() {
    return DateValue;
  }

  public void setDateValue(String dateValue) {
    DateValue = dateValue;
  }
}
