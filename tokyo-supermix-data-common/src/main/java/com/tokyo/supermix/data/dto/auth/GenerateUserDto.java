package com.tokyo.supermix.data.dto.auth;

public class GenerateUserDto {
  private Long employeeId;
  private String email;
  private Long roleId;
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
