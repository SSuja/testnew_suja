package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.EquationType;

public class EquationRequestDto {
  private Long id;
  private String formula;
  private EquationType equationType;
  private Long testConfigureId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }

  public EquationType getEquationType() {
    return equationType;
  }

  public void setEquationType(EquationType equationType) {
    this.equationType = equationType;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }
}
