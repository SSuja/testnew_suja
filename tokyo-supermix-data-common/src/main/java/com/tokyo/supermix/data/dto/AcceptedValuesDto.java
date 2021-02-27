package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AcceptedValuesDto {
  private Double minValue;
  private Double maxValue;
  private Double value;
  private Condition conditionRange;
  private boolean finalResult;
  private String testParameterName;
  private String testParameterParameterName;
}
