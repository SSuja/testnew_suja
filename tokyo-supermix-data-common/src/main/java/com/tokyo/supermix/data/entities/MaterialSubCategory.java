package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "material_sub_category")
public class MaterialSubCategory implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String prefix;
  private String name;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = false)
  private MaterialCategory materialCategory;

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

  public MaterialCategory getMaterialCategory() {
    return materialCategory;
  }

  public void setMaterialCategory(MaterialCategory materialCategory) {
    this.materialCategory = materialCategory;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

}
