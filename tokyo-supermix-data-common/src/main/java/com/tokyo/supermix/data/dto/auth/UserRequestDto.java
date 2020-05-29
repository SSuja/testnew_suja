package com.tokyo.supermix.data.dto.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRequestDto {
  private Long id;
  @NotNull(message = "{userRequestDto.userName.null}")
  @NotEmpty(message = "{userRequestDto.userName.empty}")
  private String userName;
  @NotNull(message = "{userRequestDto.password.null}")
  @NotEmpty(message = "{userRequestDto.password.empty}")
  private String password;
  private Long employeeId;
  private String email;
  private Long roleId;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

}