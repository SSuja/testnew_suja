package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestEquationRequestDto {
  private Long id;
  private Long testConfigureId;
  private Long equationId;
  private Long testParameterId;
}
