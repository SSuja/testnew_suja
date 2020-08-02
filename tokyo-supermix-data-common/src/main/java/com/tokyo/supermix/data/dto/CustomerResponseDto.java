package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.entities.Plant;

public class CustomerResponseDto {
  private Long id;
  private String name;
  private String phoneNumber;
  private String address;
  private String email;
  private List<PlantDto> plants;
  private String createdAt;
  private String updatedAt;
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
  public List<PlantDto> getPlants() {
    return plants;
  }
  public void setPlants(List<PlantDto> plants) {
    this.plants = plants;
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

 
}
