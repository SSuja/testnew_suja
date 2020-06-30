package com.tokyo.supermix.data.dto.privilege;

public class PlantRoleResponseDto {
  private Long id;
  private String RoleName;
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleName() {
    return RoleName;
  }

  public void setRoleName(String roleName) {
    RoleName = roleName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
