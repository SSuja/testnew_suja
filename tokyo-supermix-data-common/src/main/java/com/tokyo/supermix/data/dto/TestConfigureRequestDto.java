package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TestConfigureRequestDto {
  private Long id;
  @NotNull(message = "{testConfigureRequestDto.name.null}")
  @NotEmpty(message = "{testConfigureRequestDto.name.empty}")
  private String name;
  private Long testTypeId;
  private Long equationId;
  private Long testId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getTestTypeId() {
    return testTypeId;
  }

  public void setTestTypeId(Long testTypeId) {
    this.testTypeId = testTypeId;
  }

  public Long getEquationId() {
    return equationId;
  }

  public void setEquationId(Long equationId) {
    this.equationId = equationId;
  }

  public Long getTestId() {
    return testId;
  }

  public void setTestId(Long testId) {
    this.testId = testId;
  }

}
