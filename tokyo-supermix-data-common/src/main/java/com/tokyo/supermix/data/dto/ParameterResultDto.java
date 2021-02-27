package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterResultDto {
  private Long testParameterId;
  private Long testEquationId;
  private Double value;
  private String dateValue;
}
