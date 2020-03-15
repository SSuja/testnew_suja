package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "plant")
public class Plant implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String code;
  private String name;
  private String address;
  private String phoneNumber;

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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
