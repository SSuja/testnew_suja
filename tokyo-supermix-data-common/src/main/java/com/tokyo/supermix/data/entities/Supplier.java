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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "supplier")
public class Supplier extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private boolean sentMail;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "supplier_supplierCategory",
      joinColumns = {@JoinColumn(name = "supplier_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "supplierCategory_id", referencedColumnName = "id")})
  private List<SupplierCategory> supplierCategories;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;

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

  public List<SupplierCategory> getSupplierCategories() {
    return supplierCategories;
  }

  public void setSupplierCategories(List<SupplierCategory> supplierCategories) {
    this.supplierCategories = supplierCategories;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public boolean isSentMail() {
    return sentMail;
  }

  public void setSentMail(boolean sentMail) {
    this.sentMail = sentMail;
  }
}
