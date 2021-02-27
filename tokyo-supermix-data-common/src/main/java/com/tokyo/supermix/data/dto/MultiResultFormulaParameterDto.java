package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MultiResultFormulaParameterDto {
  private Long testParameterId;
  private String testParameterName;
  private String abbreviation;

}
