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
  @JoinColumn(name = "mainTestTypeCode", nullable = false)
  private MainTestType mainTestType;

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

  public MainTestType getMainTestType() {
    return mainTestType;
  }

  public void setMainTestType(MainTestType mainTestType) {
    this.mainTestType = mainTestType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
