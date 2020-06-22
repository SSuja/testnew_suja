package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubModulePlantRolePlantPermissionDto {
  private String subModule;
  private boolean status;
  private List<PlantRolePlantPermissionRequestDto> plantRolePlantPermissions;

  public String getSubModule() {
    return subModule;
  }

  public void setSubModule(String subModule) {
    this.subModule = subModule;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public List<PlantRolePlantPermissionRequestDto> getPlantRolePlantPermissions() {
    return plantRolePlantPermissions;
  }

  public void setPlantRolePlantPermissions(
      List<PlantRolePlantPermissionRequestDto> plantRolePlantPermissions) {
    this.plantRolePlantPermissions = plantRolePlantPermissions;
  }
}
