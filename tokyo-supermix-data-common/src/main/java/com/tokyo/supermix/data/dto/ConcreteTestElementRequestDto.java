package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConcreteTestElementRequestDto {
  private Long id;
  @NotNull(message = "{concreteTestElementRequestDto.name.null}")
  @NotEmpty(message = "{concreteTestElementRequestDto.name.empty}")
  private String name;
  @NotNull(message = "{concreteTestElementRequestDto.abbreviation.null}")
  @NotEmpty(message = "{concreteTestElementRequestDto.abbreviation.empty}")
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
