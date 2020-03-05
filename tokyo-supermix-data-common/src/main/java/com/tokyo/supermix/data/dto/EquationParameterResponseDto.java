package com.tokyo.supermix.data.dto;

public class EquationParameterResponseDto {
  private Long id;
  private EquationDto equation;
  private ParameterDto parameter;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EquationDto getEquation() {
    return equation;
  }

  public void setEquation(EquationDto equation) {
    this.equation = equation;
  }

  public ParameterDto getParameter() {
    return parameter;
  }

  public void setParameter(ParameterDto parameter) {
    this.parameter = parameter;
  }

}
