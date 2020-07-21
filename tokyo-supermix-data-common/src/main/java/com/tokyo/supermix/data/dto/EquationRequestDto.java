package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.EquationType;

public class EquationRequestDto {
  private Long id;
  @NotNull(message = "{equationDto.formula.null}")
  @NotEmpty(message = "{equationDto.formula.empty}")
  private String formula;
  private EquationType equationType;

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
}
