package com.tokyo.supermix.data.dto;

import java.time.LocalDateTime;
import com.tokyo.supermix.data.enums.TestParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestParameterDto {
  private Long id;
  private String parameterName;
  private String abbreviation;
  private TestParameterType entryLevel;
  private Double value;
  private UnitDto unit;
  private LocalDateTime dateValue;
}
