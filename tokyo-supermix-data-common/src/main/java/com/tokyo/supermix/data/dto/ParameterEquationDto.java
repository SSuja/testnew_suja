package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterEquationDto {
  private Long testParameterId;
  private String parameterEquation;
  private String abbreviation;

}
