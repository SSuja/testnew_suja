package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.EntryLevel;

public class TestParameterResponseDto {
  private Long id;
  private TestConfigureResponseDto testConfigure;
  private Parameter parameter;
  private String abbreviation;
  private EntryLevel entryLevel;
  private Double value;

  public Parameter getParameter() {
    return parameter;
  }

  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
  }

  public Unit getUnit() {
    return Unit;
  }

  public void setUnit(Unit unit) {
    Unit = unit;
  }

  private Unit Unit;

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

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public EntryLevel getEntryLevel() {
    return entryLevel;
  }

  public void setEntryLevel(EntryLevel entryLevel) {
    this.entryLevel = entryLevel;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }
}
