package com.tokyo.supermix.data.dto;

public class MaterialSubCategoryResponseDto {
  private Long id;
  private String name;
  private Long materialCategoryId;
  private String materialCategoryName;

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

  public Long getMaterialCategoryId() {
    return materialCategoryId;
  }

  public void setMaterialCategoryId(Long materialCategoryId) {
    this.materialCategoryId = materialCategoryId;
  }

  public String getMaterialCategoryName() {
    return materialCategoryName;
  }

  public void setMaterialCategoryName(String materialCategoryName) {
    this.materialCategoryName = materialCategoryName;
  }

}
