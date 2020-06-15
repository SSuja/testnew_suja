package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubModulePermissionDto {
  private String subModule;
  private List<PermissionDto> permissions;

  public String getSubModule() {
    return subModule;
  }

  public void setSubModule(String subModule) {
    this.subModule = subModule;
  }

  public List<PermissionDto> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<PermissionDto> permissions) {
    this.permissions = permissions;
  }

}
