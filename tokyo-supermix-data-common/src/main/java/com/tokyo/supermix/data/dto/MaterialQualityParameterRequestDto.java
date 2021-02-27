package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialQualityParameterRequestDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Condition conditionRange;
  private Long unitId;
  private Long rawMaterialId;
  private Long materialSubCategoryId;
  private Long materialCategoryId;
  private Long parameterId;
  private QualityParamaterType qualityParamaterType;
}
