package com.tokyo.supermix.data.dto.auth;

import java.util.List;
import com.tokyo.supermix.data.enums.UserType;

public class GenerateUserDto {
  private Long employeeId;
  private String email;
  private List<Long> roleIds;
  private UserType userType;
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
  
  public List<Long> getRoleIds() {
    return roleIds;
  }
  public void setRoleIds(List<Long> roleIds) {
    this.roleIds = roleIds;
  }
  public UserType getUserType() {
    return userType;
  }
  public void setUserType(UserType userType) {
    this.userType = userType;
  }
  
}
