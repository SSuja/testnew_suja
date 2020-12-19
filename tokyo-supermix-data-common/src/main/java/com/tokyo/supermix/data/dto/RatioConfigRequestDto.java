package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RatioConfigRequestDto {
  private Long id;
  @NotNull(message = "{ratioConfigDto.name.null}")
  @NotEmpty(message = "{ratioConfigDto.name.empty}")
  private String name;

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
}
