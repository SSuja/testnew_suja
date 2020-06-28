package com.tokyo.supermix.data.dto.privilege;

public class PlantRolePlantPermissionDto {
  private Long plantPermissionId;
  private String plantPermissionName;
  private Long plantRoleId;
  private boolean status;

  public Long getPlantPermissionId() {
    return plantPermissionId;
  }

  public void setPlantPermissionId(Long plantPermissionId) {
    this.plantPermissionId = plantPermissionId;
  }

  public String getPlantPermissionName() {
    return plantPermissionName;
  }

  public void setPlantPermissionName(String plantPermissionName) {
    this.plantPermissionName = plantPermissionName;
  }

  public Long getPlantRoleId() {
    return plantRoleId;
  }

  public void setPlantRoleId(Long plantRoleId) {
    this.plantRoleId = plantRoleId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
