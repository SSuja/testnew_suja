package com.tokyo.supermix.util.privilege;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ValidationMessages.properties")
public class PrivilegeValidationFailureStatusCodes {
  /*
   * Authentication
   */
  @Value("${auth.password.invalid}")
  private String password;
  @Value("${auth.emailOrUserName.invalid}")
  private String emailOrUserName;
  @Value("${auth.credentials.invalid}")
  private String credentials;
  @Value("${auth.password.match}")
  private String isMatchPassword;
  @Value("${auth.passwordToken.invalid}")
  private String isPasswordTokenFailed;
  @Value("${auth.userNotActive.invalid}")
  private String userNotActive;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmailOrUserName() {
    return emailOrUserName;
  }

  public void setEmailOrUserName(String emailOrUserName) {
    this.emailOrUserName = emailOrUserName;
  }

  public String getCredentials() {
    return credentials;
  }

  public void setCredentials(String credentials) {
    this.credentials = credentials;
  }

  public String getIsMatchPassword() {
    return isMatchPassword;
  }

  public void setIsMatchPassword(String isMatchPassword) {
    this.isMatchPassword = isMatchPassword;
  }

  public String getIsPasswordTokenFailed() {
    return isPasswordTokenFailed;
  }

  public void setIsPasswordTokenFailed(String isPasswordTokenFailed) {
    this.isPasswordTokenFailed = isPasswordTokenFailed;
  }

  public String getUserNotActive() {
    return userNotActive;
  }

  public void setUserNotActive(String userNotActive) {
    this.userNotActive = userNotActive;
  }
  /*
   * User
   */
  @Value("${validation.user.notExists}")
  private String userNotExist;
  @Value("${validation.user.aleadyExists}")
  private String userAlreadyExist;
  @Value("${userRequestDto.userName.empty}")
  private String userNameIsEmpty;
  @Value("${userRequestDto.userName.null}")
  private String userNameIsNull;
  @Value("${userRequestDto.password.empty}")
  private String passwordIsEmpty;
  @Value("${userRequestDto.password.null}")
  private String passwordIsNull;
  @Value("${validation.employeeId.null}")
  private String EmployeeIdIsNull;

  public String getUserNotExist() {
    return userNotExist;
  }

  public void setUserNotExist(String userNotExist) {
    this.userNotExist = userNotExist;
  }

  public String getUserAlreadyExist() {
    return userAlreadyExist;
  }

  public void setUserAlreadyExist(String userAlreadyExist) {
    this.userAlreadyExist = userAlreadyExist;
  }
 
  public String getEmployeeIdIsNull() {
    return EmployeeIdIsNull;
  }

  public void setEmployeeIdIsNull(String employeeIdIsNull) {
    EmployeeIdIsNull = employeeIdIsNull;
  }
  /*
   * Role
   */
  @Value("${validation.role.notExists}")
  private String roleNotExists;

  @Value("${validation.role.alreadyExists}")
  private String roleAlreadyExists;

  @Value("${validation.name.alreadyExists}")
  private String roleNameAlreadyExists;

  public String getRoleNameAlreadyExists() {
    return roleNameAlreadyExists;
  }

  public void setRoleNameAlreadyExists(String roleNameAlreadyExists) {
    this.roleNameAlreadyExists = roleNameAlreadyExists;
  }

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
  /*
   * Plant Role
   */

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

  /*
   * Plant
   */
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

  /*
   * subModule
   */
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

  /*
   * plantPermission
   */
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
  /*
   * plantAccessLevel
   */
  @Value("${validation.plantAccessLevel.notExists}")
  private String plantAccessLevelNotExists;

  @Value("${validation.plantAccessLevel.alreadyExists}")
  private String plantAccessLevelAlreadyExists;

  public String getPlantAccessLevelNotExists() {
    return plantAccessLevelNotExists;
  }

  public void setPlantAccessLevelNotExists(String plantAccessLevelNotExists) {
    this.plantAccessLevelNotExists = plantAccessLevelNotExists;
  }

  public String getPlantAccessLevelAlreadyExists() {
    return plantAccessLevelAlreadyExists;
  }

  public void setPlantAccessLevelAlreadyExists(String plantAccessLevelAlreadyExists) {
    this.plantAccessLevelAlreadyExists = plantAccessLevelAlreadyExists;
  }
}
