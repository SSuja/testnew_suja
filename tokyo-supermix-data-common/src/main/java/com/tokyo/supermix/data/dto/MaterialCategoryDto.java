package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.MainType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialCategoryDto {
  private Long id;
  @NotNull(message = "{materialCategoryDto.name.null}")
  @NotEmpty(message = "{materialCategoryDto.name.empty}")
  private String name;
  @NotNull(message = "{materialCategoryDto.prefix.null}")
  @NotEmpty(message = "{materialCategoryDto.prefix.empty}")
  private String prefix;
  private MainType mainType;
}
