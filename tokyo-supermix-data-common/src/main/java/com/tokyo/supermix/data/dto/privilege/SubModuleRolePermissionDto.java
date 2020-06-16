package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubModuleRolePermissionDto {
  private String subModule;
  private boolean status;
  private List<RolePermissionRequestDto> rolePermissions;

  public String getSubModule() {
    return subModule;
  }

  public void setSubModule(String subModule) {
    this.subModule = subModule;
  }

  public List<RolePermissionRequestDto> getRolePermissions() {
    return rolePermissions;
  }

  public void setRolePermissions(List<RolePermissionRequestDto> rolePermissions) {
    this.rolePermissions = rolePermissions;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
