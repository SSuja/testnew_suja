package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubBusinessUnitRequestDto {
  private Long id;
  @NotNull(message = "{subBusinessUnitDto.name.null}")
  @NotEmpty(message = "{subBusinessUnitDto.name.empty}")
  private String name;
  private String description;
}
