package com.tokyo.supermix.data.dto;

public class SieveSizeRequestDto {
  private Long id;
  private Double size;
  private Long materialSubCategoryId;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Double getSize() {
    return size;
  }
  public void setSize(Double size) {
    this.size = size;
  }
  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }
  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }
  
}
