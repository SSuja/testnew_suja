package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterResultResponseDto {
  private Long id;
  private Double value;
  private LocalDateTime dateValue;
  private TestParameterResponseDto testParameter;
  private MaterialTestTrialResponseDto materialTestTrial;
  private MaterialTestResponseDto materialTest;
  private TestEquationResponseDto testEquation;
}
