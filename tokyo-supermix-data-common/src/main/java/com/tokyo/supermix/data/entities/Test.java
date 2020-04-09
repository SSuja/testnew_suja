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
@Table(schema = "tokyo-supermix", name = "test")
public class Test implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "testTypeId", nullable = false)
  private TestType testType;
  @ManyToOne
  @JoinColumn(name = "equationId", nullable = true)
  private Equation equation;
  @ManyToOne
  @JoinColumn(name = "mainTestCode", nullable = false)
  private MainTest mainTest;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TestType getTestType() {
    return testType;
  }

  public void setTestType(TestType testType) {
    this.testType = testType;
  }

  public Equation getEquation() {
    return equation;
  }

  public void setEquation(Equation equation) {
    this.equation = equation;
  }

  public MainTest getMainTest() {
    return mainTest;
  }

  public void setMainTest(MainTest mainTest) {
    this.mainTest = mainTest;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
