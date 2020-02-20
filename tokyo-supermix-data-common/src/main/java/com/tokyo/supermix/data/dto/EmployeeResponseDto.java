package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmployeeResponseDto {
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
  @NotNull(message = "{employeeDto.plantCode.null}")
  @NotEmpty(message = "{employeeDto.plantCode.empty}")
  private String plantName;
  private String designationName;

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

  public String getPlantName() {
    return plantName;
  }

  public void setPlantName(String plantName) {
    this.plantName = plantName;
  }

  public String getDesignationName() {
    return designationName;
  }

  public void setDesignationName(String designationName) {
    this.designationName = designationName;
  }



}
