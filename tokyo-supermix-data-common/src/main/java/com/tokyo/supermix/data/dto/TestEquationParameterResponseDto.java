package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestEquationParameterResponseDto {
  private Long id;
  private TestParameterResponseDto testParameter;
  private TestEquationResponseDto testEquation;
}
