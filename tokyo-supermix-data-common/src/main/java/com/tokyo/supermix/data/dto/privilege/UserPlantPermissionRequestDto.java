package com.tokyo.supermix.data.dto.privilege;

public class UserPlantPermissionRequestDto {
  private Boolean status;
  private Long plantPermissionId;
  private Long userId;

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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

}
