package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmployeeRequestDto {
  private Long id;
  @NotNull(message = "{employeeDto.firstName.null}")
  @NotEmpty(message = "{employeeDto.firstName.empty}")
  private String firstName;
  @NotNull(message = "{employeeDto.lastName.null}")
  @NotEmpty(message = "{employeeDto.lastName.empty}")
  private String lastName;
  @NotNull(message = "{employeeDto.email.null}")
  @NotEmpty(message = "{employeeDto.email.empty}")
  private String email;
  private String phoneNumber;
  private String address;
  private String plantCode;
  private Long designationId;
  private boolean hasUser;
  private boolean isEnabled;
  private String profilePicPath;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public Long getDesignationId() {
    return designationId;
  }

  public void setDesignationId(Long designationId) {
    this.designationId = designationId;
  }

  public Boolean getHasUser() {
    return hasUser;
  }

  public void setHasUser(Boolean hasUser) {
    this.hasUser = hasUser;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public String getProfilePicPath() {
    return profilePicPath;
  }

  public void setProfilePicPath(String profilePicPath) {
    this.profilePicPath = profilePicPath;
  }
}
