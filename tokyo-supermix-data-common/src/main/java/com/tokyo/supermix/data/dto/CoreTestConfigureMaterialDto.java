package com.tokyo.supermix.data.dto;

public class CoreTestConfigureMaterialDto {
  private Long coreTestConfigureId;
  private Long rawMaterialId;
  private String rawMaterialName;
  private boolean coreTest;
  private Long materialSubCategoryId;

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public boolean isCoreTest() {
    return coreTest;
  }

  public void setCoreTest(boolean coreTest) {
    this.coreTest = coreTest;
  }

  public Long getCoreTestConfigureId() {
    return coreTestConfigureId;
  }

  public void setCoreTestConfigureId(Long coreTestConfigureId) {
    this.coreTestConfigureId = coreTestConfigureId;
  }
}
