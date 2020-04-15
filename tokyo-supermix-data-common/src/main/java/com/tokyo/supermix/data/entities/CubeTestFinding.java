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

@Entity
@Table(schema = "tokyo-supermix", name = "cube_test_finding")
public class CubeTestFinding implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long cubeNo;
  private Long age;
  private Double value;
  private Date date;
  @ManyToOne
  @JoinColumn(name = "finishProductSampleId", nullable = false)
  private FinishProductSample finishProductSample;
  @ManyToOne
  @JoinColumn(name = "concreteTestId", nullable = false)
  private ConcreteTest concreteTest;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCubeNo() {
    return cubeNo;
  }

  public void setCubeNo(Long cubeNo) {
    this.cubeNo = cubeNo;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public FinishProductSample getFinishProductSample() {
    return finishProductSample;
  }

  public void setFinishProductSample(FinishProductSample finishProductSample) {
    this.finishProductSample = finishProductSample;
  }

  public ConcreteTest getConcreteTest() {
    return concreteTest;
  }

  public void setConcreteTest(ConcreteTest concreteTest) {
    this.concreteTest = concreteTest;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
