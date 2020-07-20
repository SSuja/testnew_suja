package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.TestParameterType;

public class TestParameterResponseDto {
  private Long id;
  private TestConfigureResponseDto testConfigure;
  private Parameter parameter;
  private String abbreviation;
  private TestParameterType type;
  private Double value;
  private Unit unit;
  private boolean equationExists;
  private QualityParameterResponseDto qualityParameter;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TestConfigureResponseDto getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigureResponseDto testConfigure) {
    this.testConfigure = testConfigure;
  }

  public Parameter getParameter() {
    return parameter;
  }

  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
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

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public boolean isEquationExists() {
    return equationExists;
  }

  public void setEquationExists(boolean equationExists) {
    this.equationExists = equationExists;
  }

  public TestParameterType getType() {
    return type;
  }

  public void setType(TestParameterType type) {
    this.type = type;
  }

  public QualityParameterResponseDto getQualityParameter() {
    return qualityParameter;
  }

  public void setQualityParameter(QualityParameterResponseDto qualityParameter) {
    this.qualityParameter = qualityParameter;
  }
}
