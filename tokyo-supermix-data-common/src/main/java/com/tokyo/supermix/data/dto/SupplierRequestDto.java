package com.tokyo.supermix.data.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SupplierRequestDto {
  private Long id;
  @NotNull(message = "{supplierRequestDto.name.null}")
  @NotEmpty(message = "{supplierRequestDto.name.empty}")
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  @NotNull(message = "{supplierRequestDto.plantCode.null}")
  @NotEmpty(message = "{supplierRequestDto.plantCode.empty}")
  private String plantCode;
  private List<Long> suppilerCategoryIds;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public List<Long> getSuppilerCategoryIds() {
    return suppilerCategoryIds;
  }

  public void setSuppilerCategoryIds(List<Long> suppilerCategoryIds) {
    this.suppilerCategoryIds = suppilerCategoryIds;
  }
}
