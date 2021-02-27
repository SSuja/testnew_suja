package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.EquipmentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquipmentDto {
  private Long id;
  private String description;
  @NotNull(message = "{equipmentDto.name.null}")
  @NotEmpty(message = "{equipmentDto.name.empty}")
  private String name;
  private EquipmentType equipmentType;
}
