package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.EquationType;

public class EquationResponseDto {
  private Long id;
  private String formula;
  private String name;
  private EquationType equationType;
  private boolean parameterExists;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EquationType getEquationType() {
    return equationType;
  }

  public void setEquationType(EquationType equationType) {
    this.equationType = equationType;
  }

  public boolean isParameterExists() {
    return parameterExists;
  }

  public void setParameterExists(boolean parameterExists) {
    this.parameterExists = parameterExists;
  }
}
