package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductParameterResultResponseDto {
  private Long id;
  private Double result;
  private TestParameterResponseDto testParameter;
  private FinishProductTestResponseDto finishProductTest;
}
