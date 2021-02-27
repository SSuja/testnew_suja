package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterEquationElementRequestDto {
  private Long id;
  private ParameterEquationResponseDto parameterEquation;
  private Long testParameterId;
}
