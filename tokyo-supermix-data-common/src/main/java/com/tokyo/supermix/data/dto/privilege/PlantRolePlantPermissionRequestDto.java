package com.tokyo.supermix.data.dto.privilege;

public class PlantRolePlantPermissionRequestDto {
  private Boolean status;
  private Long plantPermissionId;
  private String plantPermissionName;
  private Long plantRoleId;
  private Long subModuleId;
  private Long mainModuleId;

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

  public String getPlantPermissionName() {
    return plantPermissionName;
  }

  public void setPlantPermissionName(String plantPermissionName) {
    this.plantPermissionName = plantPermissionName;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public Long getPlantPermissionId() {
    return plantPermissionId;
  }

  public void setPlantPermissionId(Long plantPermissionId) {
    this.plantPermissionId = plantPermissionId;
  }

  public Long getPlantRoleId() {
    return plantRoleId;
  }

  public void setPlantRoleId(Long plantRoleId) {
    this.plantRoleId = plantRoleId;
  }
}
