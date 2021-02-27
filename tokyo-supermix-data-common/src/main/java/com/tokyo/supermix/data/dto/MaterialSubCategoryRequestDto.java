package com.tokyo.supermix.data.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialSubCategoryRequestDto {
  private Long id;
  @NotNull(message = "{materialSubCategoryRequestDto.name.null}")
  @NotEmpty(message = "{materialSubCategoryRequestDto.name.empty}")
  private String name;
  @NotNull(message = "{materialSubCategoryRequestDto.prefix.null}")
  @NotEmpty(message = "{materialSubCategoryRequestDto.prefix.empty}")
  private String prefix;
  private Long materialCategoryId;
  private List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDto;
}
