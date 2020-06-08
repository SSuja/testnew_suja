package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class PrivilegeResponseDto {
  private String mainRoute;
  private List<SubRoutePrivilegeDto> subRoutes;
 
  public String getMainRoute() {
    return mainRoute;
  }
  public void setMainRoute(String mainRoute) {
    this.mainRoute = mainRoute;
  }
  
  public List<SubRoutePrivilegeDto> getSubRoutes() {
    return subRoutes;
  }
  public void setSubRoutes(List<SubRoutePrivilegeDto> subRoutes) {
    this.subRoutes = subRoutes;
  }
}
