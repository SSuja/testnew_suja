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
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;

@Entity
@Table(schema = "tokyo-supermix", name = "test_parameter")
public class TestParameter extends DateAudit implements Serializable {
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
  @JoinColumn(name = "unitId", nullable = true)
  private Unit unit;
  private String abbreviation;
  @Enumerated(EnumType.ORDINAL)
  private TestParameterType type;
  private Double value;
  @ManyToOne
  @JoinColumn(name = "qualityParameterId", nullable = true)
  private QualityParameter qualityParameter;
  private boolean acceptedCriteria;
  @Enumerated(EnumType.ORDINAL)
  private InputMethod inputMethods;
  private String level;

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

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public TestParameterType getType() {
    return type;
  }

  public void setType(TestParameterType type) {
    this.type = type;
  }

  public QualityParameter getQualityParameter() {
    return qualityParameter;
  }

  public void setQualityParameter(QualityParameter qualityParameter) {
    this.qualityParameter = qualityParameter;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public boolean isAcceptedCriteria() {
    return acceptedCriteria;
  }

  public void setAcceptedCriteria(boolean acceptedCriteria) {
    this.acceptedCriteria = acceptedCriteria;
  }

  public InputMethod getInputMethods() {
    return inputMethods;
  }

  public void setInputMethods(InputMethod inputMethods) {
    this.inputMethods = inputMethods;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }
  
}
