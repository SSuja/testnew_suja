package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class RolePermissionResponseDto {
  private Long mainModuleId;
  private String mainModule;
  private boolean status;
  private List<SubModuleRolePermissionDto> subModules;

  public Long getMainModuleId() {
    return mainModuleId;
  }

  public void setMainModuleId(Long mainModuleId) {
    this.mainModuleId = mainModuleId;
  }

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
