package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MaterialSubCategoryRequestDto {
  private Long id;
  @NotNull(message = "{MaterialSubCategoryRequestDto.name.null}")
  @NotEmpty(message = "{MaterialSubCategoryRequestDto.name.empty}")
  private String name;
  private Long materialCategoryId;

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
}
