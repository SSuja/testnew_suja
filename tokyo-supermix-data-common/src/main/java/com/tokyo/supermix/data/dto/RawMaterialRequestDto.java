package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Nature;

public class RawMaterialRequestDto {
  private Long id;
  @NotNull(message = "{rawMaterialRequestDto.name.null}")
  @NotEmpty(message = "{rawMaterialRequestDto.name.empty}")
  private String name;
  private Nature nature;
  private Long materialSubCategoryId;
  private String description;
  @NotNull(message = "{rawMaterialRequestDto.prefix.null}")
  @NotEmpty(message = "{rawMaterialRequestDto.prefix.empty}")
  private String prefix;

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

  public Nature getNature() {
    return nature;
  }

  public void setNature(Nature nature) {
    this.nature = nature;
  }

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }
}
