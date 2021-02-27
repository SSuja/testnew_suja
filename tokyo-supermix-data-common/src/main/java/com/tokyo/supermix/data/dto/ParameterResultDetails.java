package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterResultDetails {
  private Long testParameterId;
  private String testParameterInputMethod;
  private String testParameterType;
  private String testParameterName;
  private String TestParameterAbbrebation;
  private Double value;
  private LocalDateTime dateValue;
}
