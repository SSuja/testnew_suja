package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MultiResultFormulaResponseDto {
  private Long id;
  private Long testConfigureId;
  private String logicalFormula;
  private List<MultiResultFormulaParameterDto> multiResultFormulaParameterDtos;

}
