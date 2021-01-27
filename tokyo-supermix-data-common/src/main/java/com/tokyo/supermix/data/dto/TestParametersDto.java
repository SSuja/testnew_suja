package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.TestParameterType;

public class TestParametersDto {
  private String parameterName;
  private String abbreviation;
  private TestParameterType type;
  private String value;
  private LocalDateTime dateValue;
  private Unit unit;
  private boolean acceptedCriteria;
  private InputMethod inputMethods;
  private String level;
  private String name;
  private Long tableFormatId;
  private ParameterDataType parameterDataType;

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public TestParameterType getType() {
    return type;
  }

  public void setType(TestParameterType type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public LocalDateTime getDateValue() {
    return dateValue;
  }

  public void setDateValue(LocalDateTime dateValue) {
    this.dateValue = dateValue;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getTableFormatId() {
    return tableFormatId;
  }

  public void setTableFormatId(Long tableFormatId) {
    this.tableFormatId = tableFormatId;
  }

  public ParameterDataType getParameterDataType() {
    return parameterDataType;
  }

  public void setParameterDataType(ParameterDataType parameterDataType) {
    this.parameterDataType = parameterDataType;
  }
}
