package com.tokyo.supermix.data.dto.privilege;

public class PlantAccessLevelDto {
  private Long id;
  private String plantCode;
  private String plantName;
  private Long plantRoleId;
  private String plantRoleName;

  private boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public Long getPlantRoleId() {
    return plantRoleId;
  }

  public void setPlantRoleId(Long plantRoleId) {
    this.plantRoleId = plantRoleId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getPlantName() {
    return plantName;
  }

  public void setPlantName(String plantName) {
    this.plantName = plantName;
  }

  public String getPlantRoleName() {
    return plantRoleName;
  }

  public void setPlantRoleName(String plantRoleName) {
    this.plantRoleName = plantRoleName;
  }
}
