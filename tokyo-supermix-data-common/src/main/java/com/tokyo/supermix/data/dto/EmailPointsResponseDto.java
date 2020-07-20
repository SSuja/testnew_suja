package com.tokyo.supermix.data.dto;

public class EmailPointsResponseDto {
  private Long id;
  private String name;
  private boolean active;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private MaterialCategoryDto materialCategory;
  private TestDto test;
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
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }
  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }
  public MaterialCategoryDto getMaterialCategory() {
    return materialCategory;
  }
  public void setMaterialCategory(MaterialCategoryDto materialCategory) {
    this.materialCategory = materialCategory;
  }
  public TestDto getTest() {
    return test;
  }
  public void setTest(TestDto test) {
    this.test = test;
  }
}
