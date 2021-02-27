package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.ParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParameterDto {
  private Long id;
  @NotNull(message = "{parameterDto.name.null}")
  @NotEmpty(message = "{parameterDto.name.empty}")
  private String name;
  private ParameterType parameterType;
  private ParameterDataType parameterDataType;
}
