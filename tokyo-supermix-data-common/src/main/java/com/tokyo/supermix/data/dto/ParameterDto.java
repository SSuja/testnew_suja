package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParameterDto {
  private Long id;
  @NotNull(message = "{parameterDto.name.null}")
  @NotEmpty(message = "{parameterDto.name.empty}")
  private String name;
  @NotNull(message = "{parameterDto.abbreviation.null}")
  @NotEmpty(message = "{parameterDto.abbreviation.empty}")
  private String abbreviation;


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

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }


}
