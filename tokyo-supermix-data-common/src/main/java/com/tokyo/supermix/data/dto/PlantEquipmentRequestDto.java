package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlantEquipmentRequestDto {
  @NotNull(message = "{plantEquipmentDto.serialNo.null}")
  @NotEmpty(message = "{plantequipmentDto.serialNo.empty}")
  private String serialNo;
  private String brandName;
  private String modelName;
  private String description;
  @NotNull(message = "{plantEquipmentDto.plantCode.null}")
  @NotEmpty(message = "{plantEquipmentDto.plantCode.empty}")
  private String plantCode;
  private Long equipmentId;
  private boolean calibrationExists;
  private boolean sentMail;
}
