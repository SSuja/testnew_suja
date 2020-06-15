package com.tokyo.supermix.data.dto.privilege;

public class RolePermissionDto {
  private Long id;
  private String permission;
  private boolean status;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getPermission() {
    return permission;
  }
  public void setPermission(String permission) {
    this.permission = permission;
  }
  public boolean isStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }  
}
