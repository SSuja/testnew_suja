package com.tokyo.supermix.data.dto;

public class UserResponseDto {
  private Long id;
  private String userName;
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

}
