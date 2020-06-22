package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class PlantRolePlantPermissionResponseDto {
  private String mainModule;
  private boolean status;
  private List<SubModulePlantRolePlantPermissionDto> subModules;

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
