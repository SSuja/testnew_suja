package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MixDesignProportionResponseDto {
  private Long id;
  private Double quantity;
  private Long unitId;
  private String unit;
  private String mixDesignCode;
  private Long rawMaterialId;
  private String rawMaterialName;
  private String rawMaterialSubCategoryName;
}
