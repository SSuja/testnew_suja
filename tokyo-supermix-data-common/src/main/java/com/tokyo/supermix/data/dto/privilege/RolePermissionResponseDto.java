package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class RolePermissionResponseDto {
  private String mainModule;
  private boolean status;
  private List<SubModuleRolePermissionDto> subModules;

  public String getMainModule() {
    return mainModule;
  }

  public void setMainModule(String mainModule) {
    this.mainModule = mainModule;
  }

  public List<SubModuleRolePermissionDto> getSubModules() {
    return subModules;
  }

  public void setSubModules(List<SubModuleRolePermissionDto> subModules) {
    this.subModules = subModules;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
