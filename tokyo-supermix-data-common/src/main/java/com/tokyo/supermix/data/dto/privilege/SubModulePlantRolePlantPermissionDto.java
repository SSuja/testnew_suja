package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubModulePlantRolePlantPermissionDto {
  private Long subModuleId;
  private Long mainModuleId;
  private String subModule;
  private boolean status;
  private List<PlantRolePlantPermissionRequestDto> plantPermissions;


  public Long getSubModuleId() {
    return subModuleId;
  }

  public void setSubModuleId(Long subModuleId) {
    this.subModuleId = subModuleId;
  }

  public Long getMainModuleId() {
    return mainModuleId;
  }

  public void setMainModuleId(Long mainModuleId) {
    this.mainModuleId = mainModuleId;
  }

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

  public List<PlantRolePlantPermissionRequestDto> getPlantPermissions() {
    return plantPermissions;
  }

  public void setPlantPermissions(List<PlantRolePlantPermissionRequestDto> plantPermissions) {
    this.plantPermissions = plantPermissions;
  }

}