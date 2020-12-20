package com.tokyo.supermix.data.dto;

import java.util.List;

public class CoreTestConfigureMainDto {
  private Long materialCategoryId;
  private List<CoreTestConfigureSubCatDto> materialSubCategoryId;

  public Long getMaterialCategoryId() {
    return materialCategoryId;
  }

  public void setMaterialCategoryId(Long materialCategoryId) {
    this.materialCategoryId = materialCategoryId;
  }

  public List<CoreTestConfigureSubCatDto> getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(List<CoreTestConfigureSubCatDto> materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

}
