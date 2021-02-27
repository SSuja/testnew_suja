package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterEquationRequestDto {
  private Long id;
  private EquationResponseDto equation;
  private TestParameterResponseDto testParameter;
}
