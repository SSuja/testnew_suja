package com.tokyo.supermix.data.dto.privilege;

import com.tokyo.supermix.data.dto.PlantDto;

public class PlantPermissionResponseDto {
  private Long id;
  private String permissionName;
  private PlantDto plant;
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPermissionName() {
    return permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
