package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "employee")
public class Employee extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String address;
  private Boolean hasUser;
  private boolean isEnabled;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = true)
  private Plant plant;
  @ManyToOne
  @JoinColumn(name = "designationId", nullable = false)
  private Designation designation;
  @Column(name = "profilePicPath", nullable = true)
  private String profilePicPath;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public Designation getDesignation() {
    return designation;
  }

  public void setDesignation(Designation designation) {
    this.designation = designation;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Boolean getHasUser() {
    return hasUser;
  }

  public void setHasUser(Boolean hasUser) {
    this.hasUser = hasUser;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public String getProfilePicPath() {
    return profilePicPath;
  }

  public void setProfilePicPath(String profilePicPath) {
    this.profilePicPath = profilePicPath;
  }

 
}
