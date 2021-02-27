package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.TestParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestParametersDto {
  private String parameterName;
  private String abbreviation;
  private TestParameterType type;
  private String value;
  private LocalDateTime dateValue;
  private Unit unit;
  private boolean acceptedCriteria;
  private InputMethod inputMethods;
  private String level;
  private String name;
  private Long tableFormatId;
  private ParameterDataType parameterDataType;
}
