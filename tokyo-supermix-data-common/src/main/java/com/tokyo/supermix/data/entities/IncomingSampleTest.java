package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "incoming_sample_test")
public class IncomingSampleTest implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String testLevel;
  private Date dateAndTime;
  @ManyToOne
  @JoinColumn(name = "testCode", nullable = false)
  private Test test;
  @ManyToOne
  @JoinColumn(name = "materialStateId", nullable = false)
  private MaterialState materialState;
  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;
  @ManyToOne
  @JoinColumn(name = "incomingSampleCode", nullable = false)
  private IncomingSample incomingSample;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTestLevel() {
    return testLevel;
  }

  public void setTestLevel(String testLevel) {
    this.testLevel = testLevel;
  }

  public Date getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(Date dateAndTime) {
    this.dateAndTime = dateAndTime;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public IncomingSample getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSample incomingSample) {
    this.incomingSample = incomingSample;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
