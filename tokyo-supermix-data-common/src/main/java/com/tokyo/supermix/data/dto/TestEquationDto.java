package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestEquationDto {
  private Long testConfigId;
  private Long equationId;
  private Long testParameterId;
  private List<TestEquationParameterDto> testEquationParameters;
}
