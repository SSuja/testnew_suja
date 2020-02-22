package com.tokyo.supermix.data.dto;

public class SupplierResponseDto {
  private Long id;
  private String name;
  private String companyName;
  private String address;
  private String phoneNumber;
  private String email;
  private SupplierCategoryDto supplierCategory;

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

  public SupplierCategoryDto getSupplierCategory() {
    return supplierCategory;
  }

  public void setSupplierCategory(SupplierCategoryDto supplierCategory) {
    this.supplierCategory = supplierCategory;
  }

}
