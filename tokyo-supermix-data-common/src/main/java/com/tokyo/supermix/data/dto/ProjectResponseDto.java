package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class ProjectResponseDto {
  private String code;
  private String name;
  private String contactNumber;
  private String contactPerson;
  private Date startDate;
  private PlantDto plant;
  private CustomerResponseDto customer;

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

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public CustomerResponseDto getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerResponseDto customer) {
    this.customer = customer;
  }
}
