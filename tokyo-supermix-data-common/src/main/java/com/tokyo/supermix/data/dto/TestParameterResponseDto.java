package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestParameterResponseDto {
  private Long id;
  private TestConfigureResponseDto testConfigure;
  private Parameter parameter;
  private String abbreviation;
  private TestParameterType type;
  private Double value;
  private Unit unit;
  private QualityParameterResponseDto qualityParameter;
  private boolean acceptedCriteria;
  private InputMethod inputMethods;
  private String level;
  private String name;
  private Long tableFormatId;
  private String tableFormatName;
  private LocalDateTime dateValue;
}
