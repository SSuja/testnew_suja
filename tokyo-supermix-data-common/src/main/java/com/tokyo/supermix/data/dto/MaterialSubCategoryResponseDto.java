package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.MainType;

public class MaterialSubCategoryResponseDto {
  private Long id;
  private String name;
  private String prefix;
  private Long materialCategoryId;
  private String materialCategoryName;
  private MainType materialCategoryMainType;

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

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public MainType getMaterialCategoryMainType() {
    return materialCategoryMainType;
  }

  public void setMaterialCategoryMainType(MainType materialCategoryMainType) {
    this.materialCategoryMainType = materialCategoryMainType;
  }
}
