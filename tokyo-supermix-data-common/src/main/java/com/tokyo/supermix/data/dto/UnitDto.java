package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UnitDto {
  private Long id;
  @NotNull(message = "{unitDto.unit.null}")
  @NotEmpty(message = "{unitDto.unit.empty}")
  private String unit;
}
