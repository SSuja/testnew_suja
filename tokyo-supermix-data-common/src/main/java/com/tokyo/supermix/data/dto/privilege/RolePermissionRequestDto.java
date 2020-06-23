package com.tokyo.supermix.data.dto.privilege;

public class RolePermissionRequestDto {
  private Long roleId;
  private Long permissionId;
  private Boolean status;
  private Long mainModuleId;
  private Long subModuleId;

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
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

  public Long getMainModuleId() {
    return mainModuleId;
  }

  public void setMainModuleId(Long mainModuleId) {
    this.mainModuleId = mainModuleId;
  }

  public Long getSubModuleId() {
    return subModuleId;
  }

  public void setSubModuleId(Long subModuleId) {
    this.subModuleId = subModuleId;
  }

}
