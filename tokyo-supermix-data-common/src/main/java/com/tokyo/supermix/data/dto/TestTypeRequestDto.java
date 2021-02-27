package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestTypeRequestDto {
  private Long id;
  @NotNull(message = "{testTypeDto.type.null}")
  @NotEmpty(message = "{testTypeDto.type.empty}")
  private String type;
  private String classification;
  private Long materialSubCategoryId;
}
