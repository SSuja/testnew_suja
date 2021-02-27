package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatioConfigParameterRequestDto {
  private Long id;
  private Long ratioConfigId;
  private Long rawMaterialId;
  private String abbreviation;
  private Long unitId;
  private Double value;
}
