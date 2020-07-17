package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_test")
public class FinishProductTest extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private double result;
  private Status status;
  @ManyToOne
  @JoinColumn(name = "finishProductSampleCode", nullable = false)
  private FinishProductSample finishProductSample;
  @ManyToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public FinishProductSample getFinishProductSample() {
    return finishProductSample;
  }

  public void setFinishProductSample(FinishProductSample finishProductSample) {
    this.finishProductSample = finishProductSample;
  }

  public TestConfigure getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigure testConfigure) {
    this.testConfigure = testConfigure;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
