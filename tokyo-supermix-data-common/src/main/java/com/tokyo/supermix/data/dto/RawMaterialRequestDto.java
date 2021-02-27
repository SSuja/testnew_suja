package com.tokyo.supermix.data.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.MaterialType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RawMaterialRequestDto {
  private Long id;
  @NotNull(message = "{rawMaterialRequestDto.name.null}")
  @NotEmpty(message = "{rawMaterialRequestDto.name.empty}")
  private String name;
  private Long materialStateId;
  private Long materialSubCategoryId;
  private String description;
  @NotNull(message = "{rawMaterialRequestDto.prefix.null}")
  @NotEmpty(message = "{rawMaterialRequestDto.prefix.empty}")
  private String prefix;
  private boolean active;
  private String plantCode;
  private String erpCode;
  private Long subBusinessUnitId;
  private MaterialType materialType;
  private boolean sentMail;
  private List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDto;
}
