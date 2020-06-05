package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class PermissionResponseDto {
  private String mainRoute;
  private List<SubRoutePermissionDto> subRoutes;

  public String getMainRoute() {
    return mainRoute;
  }

  public void setMainRoute(String mainRoute) {
    this.mainRoute = mainRoute;
  }

  public List<SubRoutePermissionDto> getSubRoutes() {
    return subRoutes;
  }

  public void setSubRoutes(List<SubRoutePermissionDto> subRoutes) {
    this.subRoutes = subRoutes;
  }
}
