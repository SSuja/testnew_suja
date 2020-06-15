package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.entities.SupplierCategory;

public class SupplierResponseDto {
  private Long id;
  private String name;
  private String companyName;
  private String address;
  private String phoneNumber;
  private String email;
  private PlantDto plant;
  private List<SupplierCategory> supplierCategories;

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

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public List<SupplierCategory> getSupplierCategories() {
    return supplierCategories;
  }

  public void setSupplierCategories(List<SupplierCategory> supplierCategories) {
    this.supplierCategories = supplierCategories;
  }
}
