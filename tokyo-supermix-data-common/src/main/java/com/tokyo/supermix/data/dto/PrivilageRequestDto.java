package com.tokyo.supermix.data.dto;

public class PrivilageRequestDto {
  private String roleName;
  private Long permissionId;
  private Boolean status;

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(Long permissionId) {
    this.permissionId = permissionId;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

}
