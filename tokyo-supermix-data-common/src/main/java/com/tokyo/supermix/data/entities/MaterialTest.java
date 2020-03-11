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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestLevel;

@Entity
@Table(schema = "tokyo-supermix", name = "material_test")
public class MaterialTest implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
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
  @JoinColumn(name = "testId", nullable = false)
  private Test test;
  @ManyToOne
  @JoinColumn(name = "materialStateId", nullable = false)
  private MaterialState materialState;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
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
