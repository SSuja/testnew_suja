package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatioConfigRequestDto {
  private Long id;
  @NotNull(message = "{ratioConfigDto.name.null}")
  @NotEmpty(message = "{ratioConfigDto.name.empty}")
  private String name;
  private String description;
}
