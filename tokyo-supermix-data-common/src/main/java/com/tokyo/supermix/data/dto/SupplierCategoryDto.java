package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SupplierCategoryDto {

  private Long id;
  @NotNull(message = "{supplierCategoryDto.category.null}")
  @NotEmpty(message = "{supplierCategoryDto.category.empty}")
  private String category;
  private String description;
}
