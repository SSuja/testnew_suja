package com.tokyo.supermix.data.dto;

import java.util.List;

public class CoreTestConfigureSubCatDto {
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private Long materialCategoryId;
  private boolean coretest;
  private List<CoreTestConfigureMaterialDto> coreTestConfigureMaterialDtos;

  public Long getMaterialCategoryId() {
    return materialCategoryId;
  }

  public void setMaterialCategoryId(Long materialCategoryId) {
    this.materialCategoryId = materialCategoryId;
  }

  public boolean isCoretest() {
    return coretest;
  }

  public void setCoretest(boolean coretest) {
    this.coretest = coretest;
  }

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

  public String getMaterialSubCategoryName() {
    return materialSubCategoryName;
  }

  public void setMaterialSubCategoryName(String materialSubCategoryName) {
    this.materialSubCategoryName = materialSubCategoryName;
  }

}
