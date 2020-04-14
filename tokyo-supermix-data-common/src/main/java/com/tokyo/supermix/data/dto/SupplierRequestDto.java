package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SupplierRequestDto {
  private Long id;
  @NotNull(message = "{supplierRequestDto.name.null}")
  @NotEmpty(message = "{supplierRequestDto.name.empty}")
  private String name;
  private String companyName;
  private String address;
  @NotNull(message = "{supplierRequestDto.phoneNumber.null}")
  @NotEmpty(message = "{supplierRequestDto.phoneNumber.empty}")
  private String phoneNumber;
  @NotNull(message = "{supplierRequestDto.email.null}")
  @NotEmpty(message = "{supplierRequestDto.email.empty}")
  private String email;
  private Long supplierCategoryId;

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

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
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

  public Long getSupplierCategoryId() {
    return supplierCategoryId;
  }

  public void setSupplierCategoryId(Long supplierCategoryId) {
    this.supplierCategoryId = supplierCategoryId;
  }

}
