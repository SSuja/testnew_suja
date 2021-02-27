package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DesignationDto {
  private Long id;
  @NotNull(message = "{designationDto.name.null}")
  @NotEmpty(message = "{designationDto.name.empty}")
  private String name;
  private String description;
}
