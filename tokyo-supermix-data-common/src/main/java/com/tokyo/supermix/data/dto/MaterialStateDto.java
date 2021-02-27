package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialStateDto {
  private Long id;
  @NotNull(message = "{materialStateDto.materialState.null}")
  @NotEmpty(message = "{materialStateDto.materialState.empty}")
  private String materialState;
}
