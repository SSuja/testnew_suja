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
import com.tokyo.supermix.data.enums.Nature;

@Entity
@Table(schema = "tokyo-supermix", name = "raw_material")
public class RawMaterial implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @Enumerated(EnumType.ORDINAL)
  private Nature nature;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = false)
  private MaterialSubCategory materialSubCategory;
  private String description;
  private String prefix;

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

  public Nature getNature() {
    return nature;
  }

  public void setNature(Nature nature) {
    this.nature = nature;
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
}
