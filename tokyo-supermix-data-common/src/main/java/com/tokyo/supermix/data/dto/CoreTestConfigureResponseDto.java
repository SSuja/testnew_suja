package com.tokyo.supermix.data.dto;

import java.util.List;

public class CoreTestConfigureResponseDto {
  public Long testConfigureId;
  private Long materialCategoryId;
  private String materialCategoryName;
  private boolean coreTest;
  public List<CoreTestConfigureSubCatDto> coreTestConfigureSubCatDto;

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
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

  public boolean isCoreTest() {
    return coreTest;
  }

  public void setCoreTest(boolean coreTest) {
    this.coreTest = coreTest;
  }

  public List<CoreTestConfigureSubCatDto> getCoreTestConfigureSubCatDto() {
    return coreTestConfigureSubCatDto;
  }

  public void setCoreTestConfigureSubCatDto(
      List<CoreTestConfigureSubCatDto> coreTestConfigureSubCatDto) {
    this.coreTestConfigureSubCatDto = coreTestConfigureSubCatDto;
  }
}
