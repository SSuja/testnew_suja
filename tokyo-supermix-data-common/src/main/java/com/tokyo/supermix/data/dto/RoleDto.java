package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoleDto {
  private Long id;
  @NotNull(message = "{RoleDto.RoleName.null}")
  @NotEmpty(message = "{RoleDto.RoleName.empty}")
  private String roleName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


}
