package com.auth.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * contains custom error messages
 *
 */

@Component
@PropertySource("classpath:AuthValidationMessages.properties")
public class AuthValidationFailureCodes {
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
  
  // Authentication
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
  /*
   * Role
   */
  @Value("${validation.role.notExists}")
  private String roleNotExists;

  @Value("${validation.role.alreadyExists}")
  private String roleAlreadyExists;

  @Value("${validation.roleName.alreadyExists}")
  private String roleNameAlreadyExists;

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

  public String getUserNameIsEmpty() {
    return userNameIsEmpty;
  }

  public void setUserNameIsEmpty(String userNameIsEmpty) {
    this.userNameIsEmpty = userNameIsEmpty;
  }

  public String getUserNameIsNull() {
    return userNameIsNull;
  }

  public void setUserNameIsNull(String userNameIsNull) {
    this.userNameIsNull = userNameIsNull;
  }

  public String getPasswordIsEmpty() {
    return passwordIsEmpty;
  }

  public void setPasswordIsEmpty(String passwordIsEmpty) {
    this.passwordIsEmpty = passwordIsEmpty;
  }

  public String getPasswordIsNull() {
    return passwordIsNull;
  }

  public void setPasswordIsNull(String passwordIsNull) {
    this.passwordIsNull = passwordIsNull;
  }

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

  public String getRoleNameAlreadyExists() {
    return roleNameAlreadyExists;
  }

  public void setRoleNameAlreadyExists(String roleNameAlreadyExists) {
    this.roleNameAlreadyExists = roleNameAlreadyExists;
  }

}
