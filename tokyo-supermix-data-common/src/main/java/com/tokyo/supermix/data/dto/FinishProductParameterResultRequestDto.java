package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductParameterResultRequestDto {
  private Long id;
  private Double result;
  private Long testParameterId;
  private Long finishProductTestCode;
}
