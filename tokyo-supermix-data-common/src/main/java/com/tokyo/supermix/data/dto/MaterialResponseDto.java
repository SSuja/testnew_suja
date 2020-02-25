package com.tokyo.supermix.data.dto;

public class MaterialResponseDto {
  private String code;
  private String name;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private String materialSubCategoryMaterialCategoryId;
  private String materialSubCategoryMaterialCategoryName;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
