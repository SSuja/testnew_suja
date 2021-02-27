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
public class MaterialAcceptedValueRequestDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Long rawMaterialId;
  private Long testConfigureId;
  private Long testParameterId;
  private Long testEquationId;
  private Double value;
  private Condition conditionRange;
  private boolean finalResult;
  private Long materialSubCategoryId;
  private CategoryAcceptedType categoryAcceptedType;
  private Long materialQualityParameterId;
  private MaterialParamType materialParamType;
}
