package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class PlantRolePlantPermissionResponseDto {
  private Long mainModuleId;
  private String mainModule;
  private boolean status;
  private List<SubModulePlantRolePlantPermissionDto> subModules;

  
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

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public List<SubModulePlantRolePlantPermissionDto> getSubModules() {
    return subModules;
  }

  public void setSubModules(List<SubModulePlantRolePlantPermissionDto> subModules) {
    this.subModules = subModules;
  }
}
