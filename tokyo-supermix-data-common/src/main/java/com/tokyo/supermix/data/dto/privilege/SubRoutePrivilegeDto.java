package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubRoutePrivilegeDto {
  private String subRoute;
  private boolean status;
  private List<PrivilegeRequestDto> privileges;
  public String getSubRoute() {
    return subRoute;
  }
  public void setSubRoute(String subRoute) {
    this.subRoute = subRoute;
  }
  public List<PrivilegeRequestDto> getPrivileges() {
    return privileges;
  }
  public void setPrivileges(List<PrivilegeRequestDto> privileges) {
    this.privileges = privileges;
  }
  public boolean isStatus() {
    return status;
  }
  public void setStatus(boolean status) {
    this.status = status;
  }
  
}
