package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class QualityParameterRequestDto {
  private Long id;
  @NotNull(message = "{qualityParameterRequestDto.name.null}")
  @NotEmpty(message = "{qualityParameterRequestDto.name.empty}")
  private String name;
  @NotNull(message = "{qualityParameterRequestDto.materialSubCategoryId.null}")
  private Long materialSubCategoryId;

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

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }
}
