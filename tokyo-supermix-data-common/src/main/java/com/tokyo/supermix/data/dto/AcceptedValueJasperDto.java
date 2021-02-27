package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Condition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AcceptedValueJasperDto {
  private String minValue;
  private Condition condition;
  private String maxValue;
  private Double value;
  private String material;
}
