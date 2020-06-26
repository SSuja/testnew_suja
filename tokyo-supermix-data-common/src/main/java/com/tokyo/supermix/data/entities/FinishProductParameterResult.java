package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_parameter_result")
public class FinishProductParameterResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double result;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = false)
  private TestParameter testParameter;
  @ManyToOne
  @JoinColumn(name = "finishProductSampleId", nullable = false)
  private FinishProductSample finishProductSample;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public TestParameter getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameter testParameter) {
    this.testParameter = testParameter;
  }

  public FinishProductSample getFinishProductSample() {
    return finishProductSample;
  }

  public void setFinishProductSample(FinishProductSample finishProductSample) {
    this.finishProductSample = finishProductSample;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
