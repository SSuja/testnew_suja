package com.tokyo.supermix.data.dto;

import javax.annotation.Nullable;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.MaterialParamType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccepetedValueDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  private String conditionRange;
  private boolean finalResult;
  @Nullable
  private Long testEquationId;
  @Nullable
  private String testEquationFormula;
  @Nullable
  private Long materialId;
  @Nullable
  private String materialName;
  private Long testParameterId;
  @Nullable
  private String testParameterName;
  private String parameterName;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private CategoryAcceptedType categoryAcceptedType;
  private Long materialQualityParameterId;
  private MaterialParamType materialParamType;
}
