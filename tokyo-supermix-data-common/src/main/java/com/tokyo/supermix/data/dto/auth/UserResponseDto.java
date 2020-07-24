package com.tokyo.supermix.data.dto.auth;

import java.util.List;

import com.tokyo.supermix.data.dto.EmployeeResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;

public class UserResponseDto {
  private Long id;
  private String userName;
  private String userType;
  private EmployeeResponseDto employee;
  private String createdAt;
  private String updatedAt;
  private List<RoleDto> roles;
  private List<PlantRoleDto> plantRoles;
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }

  public List<PlantRoleDto> getPlantRoles() {
    return plantRoles;
  }

  public void setPlantRoles(List<PlantRoleDto> plantRoles) {
    this.plantRoles = plantRoles;
  }
}
