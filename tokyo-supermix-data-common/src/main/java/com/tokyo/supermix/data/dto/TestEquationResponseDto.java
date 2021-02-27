package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestEquationResponseDto {
  private Long id;
  private TestConfigureResponseDto testConfigure;
  private EquationResponseDto equation;
  private TestParameterResponseDto testParameter;
}
