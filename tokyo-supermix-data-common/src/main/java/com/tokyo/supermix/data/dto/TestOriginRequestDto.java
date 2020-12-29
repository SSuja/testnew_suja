package com.tokyo.supermix.data.dto;

public class TestOriginRequestDto {
    private Long testConfigureId;
    private Long materialCategoryId;
    private boolean coreTest;
    private Long materialSubCategoryId;
    private Long rawMaterialId;
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
    public boolean isCoreTest() {
      return coreTest;
    }
    public void setCoreTest(boolean coreTest) {
      this.coreTest = coreTest;
    }
    public Long getMaterialSubCategoryId() {
      return materialSubCategoryId;
    }
    public void setMaterialSubCategoryId(Long materialSubCategoryId) {
      this.materialSubCategoryId = materialSubCategoryId;
    }
    public Long getRawMaterialId() {
      return rawMaterialId;
    }
    public void setRawMaterialId(Long rawMaterialId) {
      this.rawMaterialId = rawMaterialId;
    }
    
}
