package com.tokyo.supermix.data.dto;

public class MultiResultFormulaResponseDto {
  private Long id;
  private Long testConfigureId;
  private String logicalFormula;

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
}
