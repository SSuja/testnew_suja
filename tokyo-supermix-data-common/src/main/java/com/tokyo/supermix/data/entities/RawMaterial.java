package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.MaterialType;

@Entity
@Table(schema = "tokyo-supermix", name = "raw_material")
public class RawMaterial extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "materialStateId", nullable = false)
  private MaterialState materialState;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = false)
  private MaterialSubCategory materialSubCategory;
  private String description;
  private String prefix;
  private boolean active;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = true)
  private Plant plant;
  private String erpCode;
  @ManyToOne
  @JoinColumn(name = "subBusinessUnitId", nullable = true)
  private SubBusinessUnit subBusinessUnit;
  @Enumerated(EnumType.ORDINAL)
  private MaterialType materialType;

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

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

  public MaterialState getMaterialState() {
    return materialState;
  }

  public void setMaterialState(MaterialState materialState) {
    this.materialState = materialState;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getErpCode() {
    return erpCode;
  }

  public void setErpCode(String erpCode) {
    this.erpCode = erpCode;
  }

  public SubBusinessUnit getSubBusinessUnit() {
    return subBusinessUnit;
  }

  public void setSubBusinessUnit(SubBusinessUnit subBusinessUnit) {
    this.subBusinessUnit = subBusinessUnit;
  }

  public MaterialType getMaterialType() {
    return materialType;
  }

  public void setMaterialType(MaterialType materialType) {
    this.materialType = materialType;
  }
}
