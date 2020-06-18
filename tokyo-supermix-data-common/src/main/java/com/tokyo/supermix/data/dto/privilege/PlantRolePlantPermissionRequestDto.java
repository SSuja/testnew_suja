package com.tokyo.supermix.data.dto.privilege;

public class PlantRolePlantPermissionRequestDto {
  private Long id;
  private Boolean status;
  private Long plantPermissionId;
  private Long plantRoleId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
