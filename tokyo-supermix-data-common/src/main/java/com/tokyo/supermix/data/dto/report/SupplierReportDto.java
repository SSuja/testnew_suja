package com.tokyo.supermix.data.dto.report;

public class SupplierReportDto {
  private Long id;
  private String name;
  private String companyName;
  private String address;
  private String phoneNumber;
  private String email;
  private String supplierCategoryName;
  private String plantName;
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
  public String getSupplierCategoryName() {
    return supplierCategoryName;
  }
  public void setSupplierCategoryName(String supplierCategoryName) {
    this.supplierCategoryName = supplierCategoryName;
  }
  public String getPlantName() {
    return plantName;
  }
  public void setPlantName(String plantName) {
    this.plantName = plantName;
  }
}
