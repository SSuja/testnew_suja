package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TestRequestDto {
  private Long id;
  @NotNull(message = "{testRequestDto.name.null}")
  @NotEmpty(message = "{testRequestDto.name.empty}")
  private String name;
  private Long testTypeId;
  private Long equationId;

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

}
