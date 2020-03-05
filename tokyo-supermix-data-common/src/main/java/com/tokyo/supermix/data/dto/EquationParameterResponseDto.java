package com.tokyo.supermix.data.dto;

public class EquationParameterResponseDto {
  private Long id;
  private Long parameterId;
  private String parameterName;
  private String parameterAbbreviation;
  private String equationFormula;
  private Long equationId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParameterId() {
    return parameterId;
  }

  public void setParameterId(Long parameterId) {
    this.parameterId = parameterId;
  }

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public String getParameterAbbreviation() {
    return parameterAbbreviation;
  }

  public void setParameterAbbreviation(String parameterAbbreviation) {
    this.parameterAbbreviation = parameterAbbreviation;
  }

  public String getEquationFormula() {
    return equationFormula;
  }

  public void setEquationFormula(String equationFormula) {
    this.equationFormula = equationFormula;
  }

  public Long getEquationId() {
    return equationId;
  }

  public void setEquationId(Long equationId) {
    this.equationId = equationId;
  }

}
