package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterResultRequestDto {
  private Long id;
  private MaterialTestTrialRequestDto materialTestTrial;
  private Double value;
  private Long testParameterId;
  private String materialTestCode;
  private Long testEquationId;
  private LocalDateTime dateValue;
}
