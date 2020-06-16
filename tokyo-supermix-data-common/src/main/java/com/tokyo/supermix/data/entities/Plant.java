package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

@Entity
@Table(schema = "tokyo-supermix", name = "plant")
public class Plant implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String name;
  private String address;
  private String phoneNumber;
  private String description;
  private String faxNumber;
  @OneToMany(mappedBy = "plant")
  Set<PlantRole> plantRole;
  @OneToMany(mappedBy = "plant")
  Set<PlantPermission> plantPermission;

  public Set<PlantRole> getPlantRole() {
    return plantRole;
  }

  public void setPlantRole(Set<PlantRole> plantRole) {
    this.plantRole = plantRole;
  }

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

  public Set<PlantPermission> getPlantPermission() {
    return plantPermission;
  }

  public void setPlantPermission(Set<PlantPermission> plantPermission) {
    this.plantPermission = plantPermission;
  }


}
