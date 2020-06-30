package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PlantDto {
  @NotNull(message = "{plantDto.id.null}")
  private String code;
  @NotNull(message = "{plantDto.name.null}")
  @NotEmpty(message = "{plantDto.name.empty}")
  private String name;
  private String address;
  @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$",
      message = "{plantDto.phoneNumber.specialCharacter}")
  private String phoneNumber;
  private String description;
  private String faxNumber;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFaxNumber() {
    return faxNumber;
  }

  public void setFaxNumber(String faxNumber) {
    this.faxNumber = faxNumber;
  }

}
