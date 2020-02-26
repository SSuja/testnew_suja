package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Nature;

public class RawMaterialResponseDto {
  private Long id;
  private String name;
  private Nature nature;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private String materialSubCategoryMaterialCategoryId;
  private String materialSubCategoryMaterialCategoryName;

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

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

  public String getMaterialSubCategoryName() {
    return materialSubCategoryName;
  }

  public void setMaterialSubCategoryName(String materialSubCategoryName) {
    this.materialSubCategoryName = materialSubCategoryName;
  }

  public String getMaterialSubCategoryMaterialCategoryId() {
    return materialSubCategoryMaterialCategoryId;
  }

  public void setMaterialSubCategoryMaterialCategoryId(
      String materialSubCategoryMaterialCategoryId) {
    this.materialSubCategoryMaterialCategoryId = materialSubCategoryMaterialCategoryId;
  }

  public String getMaterialSubCategoryMaterialCategoryName() {
    return materialSubCategoryMaterialCategoryName;
  }

  public void setMaterialSubCategoryMaterialCategoryName(
      String materialSubCategoryMaterialCategoryName) {
    this.materialSubCategoryMaterialCategoryName = materialSubCategoryMaterialCategoryName;
  }
}
