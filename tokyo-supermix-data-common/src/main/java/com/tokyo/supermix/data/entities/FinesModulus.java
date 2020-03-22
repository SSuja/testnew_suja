package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "finess_modulus")
public class FinesModulus implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double minimumValue;
  private Double maximumValue;
  @OneToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = false)
  private MaterialSubCategory materialSubCategory;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getMinimumValue() {
    return minimumValue;
  }

  public void setMinimumValue(Double minimumValue) {
    this.minimumValue = minimumValue;
  }

  public Double getMaximumValue() {
    return maximumValue;
  }

  public void setMaximumValue(Double maximumValue) {
    this.maximumValue = maximumValue;
  }

  public MaterialSubCategory getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
