package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class PermissionResponseDto {
  private String mainModule;
  private List<SubModulePermissionDto> subModules;

  public String getMainModule() {
    return mainModule;
  }

  public void setMainModule(String mainModule) {
    this.mainModule = mainModule;
  }

  public List<SubModulePermissionDto> getSubModules() {
    return subModules;
  }

  public void setSubModules(List<SubModulePermissionDto> subModules) {
    this.subModules = subModules;
  }
}
