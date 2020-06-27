package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.enums.TrailResult;

public class TestParameterRequestDto {
  private Long id;
  private Long testConfigureId;
  private Long parameterId;
  private Long unitId;
  @NotNull(message = "{testParameterDto.abbreviation.null}")
  @NotEmpty(message = "{testParameterDto.abbreviation.empty}")
  private String abbreviation;
  private TestParameterType entryLevel;
  private Double value;
  private boolean equationExists;
  private TrailResult trailResult;

  public TrailResult getTrailResult() {
    return trailResult;
  }

  public void setTrailResult(TrailResult trailResult) {
    this.trailResult = trailResult;
  }

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
}
