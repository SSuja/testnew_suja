package com.tokyo.supermix.data.dto.privilege;

public class PlantRolePlantPermissionDto {
  private Long id;
  private String plantPermissionName;
  private String permissionName;
  private Long plantRoleId;
  private boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlantPermissionName() {
    return plantPermissionName;
  }

  public void setPlantPermissionName(String plantPermissionName) {
    this.plantPermissionName = plantPermissionName;
  }

  public String getPermissionName() {
    return permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
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
