package com.tokyo.supermix.data.dto.auth;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoleRequestDto {
  private Long id;
  @NotNull(message = "{RoleDto.RoleName.null}")
  @NotEmpty(message = "{RoleDto.RoleName.empty}")
  private String roleName;
  private List<Long> permissionIds;
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

  public List<Long> getPermissionIds() {
    return permissionIds;
  }

  public void setPermissionIds(List<Long> permissionIds) {
    this.permissionIds = permissionIds;
  }
  
}
