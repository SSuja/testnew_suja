package com.tokyo.supermix.data.dto;

public class UserResponseDto {
  private Long id;
  private String username;
  private EmployeeResponseDto employee;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public EmployeeResponseDto getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeResponseDto employee) {
    this.employee = employee;
  }

}
