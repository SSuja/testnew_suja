package com.tokyo.supermix.data.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerRequestDto {
  private Long id;
  @NotNull(message = "{customerDto.name.null}")
  @NotEmpty(message = "{customerDto.name.empty}")
  private String name;
  private String phoneNumber;
  private String address;
  private String email;
  @NotNull(message = "{customerDto.plantCode.null}")
  @NotEmpty(message = "{customerDto.plantCode.empty}")
  private List<String> plantCodes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<String> getPlantCodes() {
    return plantCodes;
  }

  public void setPlantCodes(List<String> plantCodes) {
    this.plantCodes = plantCodes;
  }
}
