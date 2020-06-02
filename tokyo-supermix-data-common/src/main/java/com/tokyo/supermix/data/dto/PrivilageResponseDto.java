package com.tokyo.supermix.data.dto;

import java.util.List;

public class PrivilageResponseDto {
  private String mainRoute;
  private String subRoute;
  private List<PermissionDto> permissions;

  public String getMainRoute() {
    return mainRoute;
  }

  public void setMainRoute(String mainRoute) {
    this.mainRoute = mainRoute;
  }

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
