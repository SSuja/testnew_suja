package com.tokyo.supermix.data.dto.auth;

import java.util.List;

public class PrivilegeRouteDto {
  private String mainRoute;
  private List<SubRouteDto> subRoutes;

  public String getMainRoute() {
    return mainRoute;
  }

  public void setMainRoute(String mainRoute) {
    this.mainRoute = mainRoute;
  }

  public List<SubRouteDto> getSubRoutes() {
    return subRoutes;
  }

  public void setSubRoutes(List<SubRouteDto> subRoutes) {
    this.subRoutes = subRoutes;
  }

}
