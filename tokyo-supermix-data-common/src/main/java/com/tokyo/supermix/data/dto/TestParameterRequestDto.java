package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.EntryLevel;

public class TestParameterRequestDto {
  private Long id;
  private Long testConfigureId;
  private Long parameterId;
  private Long unitId;
  @NotNull(message = "{testParameterDto.abbreviation.null}")
  @NotEmpty(message = "{testParameterDto.abbreviation.empty}")
  private String abbreviation;
  private EntryLevel entryLevel;
  private Double value;
  private boolean equation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public Long getParameterId() {
    return parameterId;
  }

  public void setParameterId(Long parameterId) {
    this.parameterId = parameterId;
  }

  public Long getUnitId() {
    return unitId;
  }

  public void setUnitId(Long unitId) {
    this.unitId = unitId;
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

  public boolean isEquation() {
    return equation;
  }

  public void setEquation(boolean equation) {
    this.equation = equation;
  }
}
