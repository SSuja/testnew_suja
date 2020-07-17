package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.MainType;

public class MaterialCategoryDto {
  private Long id;
  @NotNull(message = "{materialCategoryDto.name.null}")
  @NotEmpty(message = "{materialCategoryDto.name.empty}")
  private String name;
  @NotNull(message = "{materialCategoryDto.prefix.null}")
  @NotEmpty(message = "{materialCategoryDto.prefix.empty}")
  private String prefix;
  private MainType mainType;

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

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public MainType getMainType() {
    return mainType;
  }

  public void setMainType(MainType mainType) {
    this.mainType = mainType;
  }
}
