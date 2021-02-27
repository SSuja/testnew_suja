package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.EquationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquationRequestDto {
  private Long id;
  private String formula;
  private EquationType equationType;
  private Long testConfigureId;
}
