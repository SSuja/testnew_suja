package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AcceptedValueResponseDto {
  private Long id;
  private Double minValue;
  private Double maxValue;
  private TestConfigureResponseDto testConfigure;
  private Double value;
  private TestEquationResponseDto testEquation;
  private Condition conditionRange;
  private boolean finalResult;
  private TestParameterResponseDto testParameter;
}
