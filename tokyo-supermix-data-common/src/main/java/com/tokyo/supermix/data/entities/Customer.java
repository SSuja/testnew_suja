package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "customer")
public class Customer extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String phoneNumber;
  private String address;
  private String email;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "customer_customerPlant",
      joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "plant_code", referencedColumnName = "code")})
  private List<Plant> plant;
  private boolean sentMail;

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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public List<Plant> getPlant() {
    return plant;
  }

  public void setPlant(List<Plant> plant) {
    this.plant = plant;
  }

  public boolean isSentMail() {
    return sentMail;
  }

  public void setSentMail(boolean sentMail) {
    this.sentMail = sentMail;
  }
}
