package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "concrete_test_result")
public class ConcreteTestResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double slump;
  private Double temperature;
  private Double waterContent;
  private Double slumpGradeRatio;
  private Double waterCementRatio;
  private Double waterBinderRatio;
  private Double strengthGradeRatio;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  private Date date;
  private Long age;
  private Double result;
  @ManyToOne
  @JoinColumn(name = "concreteTestId", nullable = false)
  private ConcreteTest concreteTest;
  @ManyToOne
  @JoinColumn(name = "finishProductSampleId", nullable = false)
  private FinishProductSample finishProductSample;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getSlump() {
    return slump;
  }

  public void setSlump(Double slump) {
    this.slump = slump;
  }

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Double getWaterContent() {
    return waterContent;
  }

  public void setWaterContent(Double waterContent) {
    this.waterContent = waterContent;
  }

  public Double getSlumpGradeRatio() {
    return slumpGradeRatio;
  }

  public void setSlumpGradeRatio(Double slumpGradeRatio) {
    this.slumpGradeRatio = slumpGradeRatio;
  }

  public Double getWaterCementRatio() {
    return waterCementRatio;
  }

  public void setWaterCementRatio(Double waterCementRatio) {
    this.waterCementRatio = waterCementRatio;
  }

  public Double getWaterBinderRatio() {
    return waterBinderRatio;
  }

  public void setWaterBinderRatio(Double waterBinderRatio) {
    this.waterBinderRatio = waterBinderRatio;
  }

  public Double getStrengthGradeRatio() {
    return strengthGradeRatio;
  }

  public void setStrengthGradeRatio(Double strengthGradeRatio) {
    this.strengthGradeRatio = strengthGradeRatio;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public ConcreteTest getConcreteTest() {
    return concreteTest;
  }

  public void setConcreteTest(ConcreteTest concreteTest) {
    this.concreteTest = concreteTest;
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
