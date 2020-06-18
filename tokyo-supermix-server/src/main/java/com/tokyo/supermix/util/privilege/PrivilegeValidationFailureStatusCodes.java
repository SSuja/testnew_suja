package com.tokyo.supermix.util.privilege;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ValidationMessages.properties")
public class PrivilegeValidationFailureStatusCodes {
  @Value("${validation.plantRole.notExists}")
  private String plantRoleNotExist;

  @Value("${validation.plantRole.alreadyExist}")
  private String plantRoleAlreadyExist;

  public String getPlantRoleNotExist() {
    return plantRoleNotExist;
  }

  public void setPlantRoleNotExist(String plantRoleNotExist) {
    this.plantRoleNotExist = plantRoleNotExist;
  }

  public String getPlantRoleAlreadyExist() {
    return plantRoleAlreadyExist;
  }

  public void setPlantRoleAlreadyExist(String plantRoleAlreadyExist) {
    this.plantRoleAlreadyExist = plantRoleAlreadyExist;
  }

  @Value("${validation.plant.notExists}")
  private String plantNotExist;

  @Value("${validation.plant.alreadyExist}")
  private String plantAlreadyExist;

  public String getPlantNotExist() {
    return plantNotExist;
  }

  public void setPlantNotExist(String plantNotExist) {
    this.plantNotExist = plantNotExist;
  }

  public String getPlantAlreadyExist() {
    return plantAlreadyExist;
  }

  public void setPlantAlreadyExist(String plantAlreadyExist) {
    this.plantAlreadyExist = plantAlreadyExist;
  }

  @Value("${validation.role.notExists}")
  private String roleNotExists;

  @Value("${validation.role.alreadyExists}")
  private String roleAlreadyExists;

  public String getRoleNotExists() {
    return roleNotExists;
  }

  public void setRoleNotExists(String roleNotExists) {
    this.roleNotExists = roleNotExists;
  }

  public String getRoleAlreadyExists() {
    return roleAlreadyExists;
  }

  public void setRoleAlreadyExists(String roleAlreadyExists) {
    this.roleAlreadyExists = roleAlreadyExists;
  }

  @Value("${validation.subModule.notExists}")
  private String subModuleNotExists;

  @Value("${validation.subModule.alreadyExists}")
  private String subModuleAlreadyExists;

  public String getSubModuleNotExists() {
    return subModuleNotExists;
  }

  public void setSubModuleNotExists(String subModuleNotExists) {
    this.subModuleNotExists = subModuleNotExists;
  }

  public String getSubModuleAlreadyExists() {
    return subModuleAlreadyExists;
  }

  public void setSubModuleAlreadyExists(String subModuleAlreadyExists) {
    this.subModuleAlreadyExists = subModuleAlreadyExists;
  }

  @Value("${validation.plantPermission.notExists}")
  private String plantPermissionNotExists;

  @Value("${validation.plantPermission.alreadyExists}")
  private String plantPermissionAlreadyExists;

  public String getPlantPermissionNotExists() {
    return plantPermissionNotExists;
  }

  public void setPlantPermissionNotExists(String plantPermissionNotExists) {
    this.plantPermissionNotExists = plantPermissionNotExists;
  }

  public String getPlantPermissionAlreadyExists() {
    return plantPermissionAlreadyExists;
  }

  public void setPlantPermissionAlreadyExists(String plantPermissionAlreadyExists) {
    this.plantPermissionAlreadyExists = plantPermissionAlreadyExists;
  }
}
