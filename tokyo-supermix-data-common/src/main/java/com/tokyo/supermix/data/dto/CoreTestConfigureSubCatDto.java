package com.tokyo.supermix.data.dto;

import java.util.List;

public class CoreTestConfigureSubCatDto {
  private Long materialSubCategoryId;
  private List<CoreTestConfigureMaterialDto> coreTestConfigureMaterialDtos;

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

  public List<CoreTestConfigureMaterialDto> getCoreTestConfigureMaterialDtos() {
    return coreTestConfigureMaterialDtos;
  }

  public void setCoreTestConfigureMaterialDtos(
      List<CoreTestConfigureMaterialDto> coreTestConfigureMaterialDtos) {
    this.coreTestConfigureMaterialDtos = coreTestConfigureMaterialDtos;
  }

}
