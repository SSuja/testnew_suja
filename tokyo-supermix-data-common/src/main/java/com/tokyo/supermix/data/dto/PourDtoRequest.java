package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PourDtoRequest {
  private Long id;
  @NotNull(message = "{PourDto.name.null}")
  @NotEmpty(message = "{PourDto.name.empty}")
  private String name;
  private String description;
  @NotNull(message = "{PourDto.projectCode.null}")
  @NotEmpty(message = "{PourDto.projectCode.empty}")
  private String projectCode;
}
