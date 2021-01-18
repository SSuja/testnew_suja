package com.tokyo.supermix.data.dto;

import java.util.List;

public class MultiResultFormulaResponseDto {
  private Long id;
  private Long testConfigureId;
  private String logicalFormula;
  private List<MultiResultFormulaParameterDto> multiResultFormulaParameterDtos;

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

  public String getLogicalFormula() {
    return logicalFormula;
  }

  public void setLogicalFormula(String logicalFormula) {
    this.logicalFormula = logicalFormula;
  }

  public List<MultiResultFormulaParameterDto> getMultiResultFormulaParameterDtos() {
    return multiResultFormulaParameterDtos;
  }

  public void setMultiResultFormulaParameterDtos(
      List<MultiResultFormulaParameterDto> multiResultFormulaParameterDtos) {
    this.multiResultFormulaParameterDtos = multiResultFormulaParameterDtos;
  }
}
