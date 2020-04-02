package com.tokyo.supermix.data.dto;

public class TestTypeResponseDto {
  private Long id;
  private String type;
  private String classification;
  private MaterialSubCategoryResponseDto materialSubCategory;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }


}
