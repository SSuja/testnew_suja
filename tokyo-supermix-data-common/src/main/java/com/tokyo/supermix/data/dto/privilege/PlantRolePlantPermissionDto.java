package com.tokyo.supermix.data.dto.privilege;

public class PlantRolePlantPermissionDto {
  private Long id;
  private String plantPermission;
  private String permission;
  private Long plantRoleId;
  private boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlantPermission() {
    return plantPermission;
  }

  public void setPlantPermission(String plantPermission) {
    this.plantPermission = plantPermission;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
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
