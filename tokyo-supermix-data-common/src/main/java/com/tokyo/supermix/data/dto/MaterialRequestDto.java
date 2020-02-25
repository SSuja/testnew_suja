package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MaterialRequestDto {
  @NotNull(message = "{materialRequestDto.code.null}")
  private String code;
  @NotNull(message = "{materialRequestDto.name.null}")
  @NotEmpty(message = "{materialRequestDto.name.empty}")
  private String name;
  private Long materialSubCategoryId;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

}
