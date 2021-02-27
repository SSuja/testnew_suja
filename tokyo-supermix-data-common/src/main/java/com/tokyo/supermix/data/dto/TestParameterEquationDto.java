package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestParameterEquationDto {
  private List<TestParameterResponseDto> testParameters;
  private List<ParameterEquationDto> parameterEquations;
}
