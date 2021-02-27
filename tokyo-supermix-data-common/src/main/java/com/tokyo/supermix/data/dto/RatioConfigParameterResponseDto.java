package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatioConfigParameterResponseDto {
  private Long id;
  private Long ratioConfigId;
  private String ratioConfigName;
  private Long rawMaterialId;
  private String rawMaterialName;
  private String abbreviation;
  private Long unitId;
  private String unitUnit;
  private Double value;
}
