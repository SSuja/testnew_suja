package com.tokyo.supermix.data.dto.auth;

import java.util.List;

public class SubRouteDto {
  private String name;
  private List<String> permissions;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

}
