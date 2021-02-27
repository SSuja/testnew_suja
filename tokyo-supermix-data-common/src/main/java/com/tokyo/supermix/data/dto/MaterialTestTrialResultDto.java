package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialTestTrialResultDto {
  private String abbrevation;
  private List<AbbrevationAndValueDto> abbrevationAndValues;
}
