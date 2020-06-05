package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubRoutePermissionDto {
  private String subRoute;
  private List<PermissionDto> permissions;
  public String getSubRoute() {
    return subRoute;
  }
  public void setSubRoute(String subRoute) {
    this.subRoute = subRoute;
  }
  public List<PermissionDto> getPermissions() {
    return permissions;
  }
  public void setPermissions(List<PermissionDto> permissions) {
    this.permissions = permissions;
  }
  
}
