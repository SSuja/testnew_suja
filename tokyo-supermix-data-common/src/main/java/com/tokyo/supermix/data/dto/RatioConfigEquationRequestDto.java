package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatioConfigEquationRequestDto {
  private Long id;
  @NotNull(message = "{ratioConfigDto.ratio.null}")
  @NotEmpty(message = "{ratioConfigDto.ratio.empty}")
  private String ratio;
  private Long ratioConfigId;
}
