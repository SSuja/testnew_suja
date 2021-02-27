package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialQualityParameterResponseDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Long unitId;
  private String unitUnit;
  private Condition conditionRange;
  private Long rawMaterialId;
  private String rawMaterialName;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private Long materialCategoryId;
  private String materialCategoryName;
  private Long parameterId;
  private String parameterName;
  private QualityParamaterType qualityParamaterType;
}
