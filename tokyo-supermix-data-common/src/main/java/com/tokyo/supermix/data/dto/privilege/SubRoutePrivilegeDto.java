package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubRoutePrivilegeDto {
  private String subRoute;
  private List<PrivilegeDto> privileges;
  public String getSubRoute() {
    return subRoute;
  }
  public void setSubRoute(String subRoute) {
    this.subRoute = subRoute;
  }
  public List<PrivilegeDto> getPrivileges() {
    return privileges;
  }
  public void setPrivileges(List<PrivilegeDto> privileges) {
    this.privileges = privileges;
  }
  
}
