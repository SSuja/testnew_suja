package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QualityParameterRequestDto {
  private Long id;
  @NotNull(message = "{qualityParameterRequestDto.name.null}")
  @NotEmpty(message = "{qualityParameterRequestDto.name.empty}")
  private String name;
}
