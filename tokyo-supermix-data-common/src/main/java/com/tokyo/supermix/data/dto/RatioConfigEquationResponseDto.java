package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatioConfigEquationResponseDto {
  private Long id;
  private String ratio;
  private Long ratioConfigId;
}
