package com.tokyo.supermix.data.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MaterialSubCategoryRequestDto {
  private Long id;
  @NotNull(message = "{materialSubCategoryRequestDto.name.null}")
  @NotEmpty(message = "{materialSubCategoryRequestDto.name.empty}")
  private String name;
  @NotNull(message = "{materialSubCategoryRequestDto.prefix.null}")
  @NotEmpty(message = "{materialSubCategoryRequestDto.prefix.empty}")
  private String prefix;
  private Long materialCategoryId;
  private List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDto;

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

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

  public List<MaterialQualityParameterRequestDto> getMaterialQualityParameterRequestDto() {
    return materialQualityParameterRequestDto;
  }

  public void setMaterialQualityParameterRequestDto(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDto) {
    this.materialQualityParameterRequestDto = materialQualityParameterRequestDto;
  }
}
