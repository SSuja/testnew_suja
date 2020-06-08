package com.tokyo.supermix.data.dto.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoleDto {
  private Long id;
  @NotNull(message = "{RoleDto.name.null}")
  @NotEmpty(message = "{RoleDto.name.empty}")
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
