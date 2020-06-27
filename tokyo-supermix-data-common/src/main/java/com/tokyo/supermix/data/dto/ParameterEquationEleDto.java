package com.tokyo.supermix.data.dto;

import java.util.List;

public class ParameterEquationEleDto {
  private Long equationId;
  private Long testParameterId;
  private List<ParameterEquationElementDto> parameterEquationElements;
  public Long getEquationId() {
    return equationId;
  }
  public void setEquationId(Long equationId) {
    this.equationId = equationId;
  }
  public Long getTestParameterId() {
    return testParameterId;
  }
  public void setTestParameterId(Long testParameterId) {
    this.testParameterId = testParameterId;
  }
  public List<ParameterEquationElementDto> getParameterEquationElements() {
    return parameterEquationElements;
  }
  public void setParameterEquationElements(
      List<ParameterEquationElementDto> parameterEquationElements) {
    this.parameterEquationElements = parameterEquationElements;
  }
}
