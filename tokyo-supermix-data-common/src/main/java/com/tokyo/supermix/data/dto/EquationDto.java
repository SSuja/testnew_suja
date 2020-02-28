package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EquationDto {
  private Long id;
  @NotNull(message = "{equationDto.formula.null}")
  @NotEmpty(message = "{equationDto.formula.empty}")
  private String formula;

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
}
