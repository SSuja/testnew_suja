package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestLevel;

@Entity
@Table(schema = "tokyo-supermix", name = "material_test")
public class MaterialTest implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Date date;
  private Long noOfTrial;
  private Double average;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @Enumerated(EnumType.ORDINAL)
  private TestLevel testLevel;
  @OneToOne
  @JoinColumn(name = "incomingSampleCode", nullable = false)
  private IncomingSample incomingSample;
  @ManyToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  @ManyToOne
  @JoinColumn(name = "materialStateId", nullable = false)
  private MaterialState materialState;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }

  public Double getAverage() {
    return average;
  }

  public void setAverage(Double average) {
    this.average = average;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public TestLevel getTestLevel() {
    return testLevel;
  }

  public void setTestLevel(TestLevel testLevel) {
    this.testLevel = testLevel;
  }

  public IncomingSample getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSample incomingSample) {
    this.incomingSample = incomingSample;
  }

  public TestConfigure getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigure testConfigure) {
    this.testConfigure = testConfigure;
  }

  public MaterialState getMaterialState() {
    return materialState;
  }

  public void setMaterialState(MaterialState materialState) {
    this.materialState = materialState;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
