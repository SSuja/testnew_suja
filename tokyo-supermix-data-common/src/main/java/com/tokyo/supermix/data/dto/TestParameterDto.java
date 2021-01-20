package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import com.tokyo.supermix.data.enums.TestParameterType;

public class TestParameterDto {
  private Long id;
  private String parameterName;
  private String abbreviation;
  private TestParameterType entryLevel;
  private Double value;
  private UnitDto unit;
  private LocalDateTime dateValue;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public UnitDto getUnit() {
    return unit;
  }

  public void setUnit(UnitDto unit) {
    this.unit = unit;
  }

  public LocalDateTime getDateValue() {
    return dateValue;
  }

  public void setDateValue(LocalDateTime dateValue) {
    this.dateValue = dateValue;
  }
}
