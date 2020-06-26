package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.TestParameterType;

@Entity
@Table(schema = "tokyo-supermix", name = "test_parameter")
public class TestParameter implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  @ManyToOne
  @JoinColumn(name = "parameterId", nullable = true)
  private Parameter parameter;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;
  private String abbreviation;
  @Enumerated(EnumType.ORDINAL)
  private TestParameterType entryLevel;
  private Double value;
  private boolean equationExists;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public TestConfigure getTestConfigure() {
    return testConfigure;
  }
  public void setTestConfigure(TestConfigure testConfigure) {
    this.testConfigure = testConfigure;
  }
  public Parameter getParameter() {
    return parameter;
  }
  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
  }
  public Unit getUnit() {
    return unit;
  }
  public void setUnit(Unit unit) {
    this.unit = unit;
  }
  public String getAbbreviation() {
    return abbreviation;
  }
  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
  public TestParameterType getEntryLevel() {
    return entryLevel;
  }
  public void setEntryLevel(TestParameterType entryLevel) {
    this.entryLevel = entryLevel;
  }
  public Double getValue() {
    return value;
  }
  public void setValue(Double value) {
    this.value = value;
  }
  public boolean isEquationExists() {
    return equationExists;
  }
  public void setEquationExists(boolean equationExists) {
    this.equationExists = equationExists;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
