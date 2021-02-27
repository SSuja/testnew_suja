package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MultiResultFormulaRequestDto {
  private Long id;
  private Long testConfigureId;
  private String logicalFormula;

}
