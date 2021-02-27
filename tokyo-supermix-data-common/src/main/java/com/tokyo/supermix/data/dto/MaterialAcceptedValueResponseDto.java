package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.MaterialParamType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialAcceptedValueResponseDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private RawMaterialResponseDto rawMaterial;
  private TestConfigureResponseDto testConfigure;
  private Double value;
  private TestParameterResponseDto testParameter;
  private Condition conditionRange;
  private boolean finalResult;
  private TestEquationResponseDto testEquation;
  private Long materialSubCategoryId;
  private CategoryAcceptedType categoryAcceptedType;
  private Long materialQualityParameterId;
  private MaterialParamType materialParamType;
}
