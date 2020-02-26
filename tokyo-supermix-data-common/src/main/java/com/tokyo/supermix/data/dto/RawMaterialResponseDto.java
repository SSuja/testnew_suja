package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Nature;

public class RawMaterialResponseDto {
  private Long id;
  private String name;
  private Nature nature;
  private MaterialSubCategoryResponseDto materialSubCategory;

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

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

}
