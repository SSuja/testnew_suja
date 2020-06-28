package com.tokyo.supermix.data.dto.auth;

import com.tokyo.supermix.data.dto.EmployeeResponseDto;

public class UserResponseDto {
  private Long id;
  private String userName;
  private String userType;
  private EmployeeResponseDto employee;

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

  public EmployeeResponseDto getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeResponseDto employee) {
    this.employee = employee;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

}
