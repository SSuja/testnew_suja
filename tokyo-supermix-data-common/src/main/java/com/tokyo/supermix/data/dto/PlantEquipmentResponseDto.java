package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.EquipmentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlantEquipmentResponseDto {
  private String serialNo;
  private String brandName;
  private String modelName;
  private String description;
  private String plantName;
  private String equipmentName;
  private PlantDto plant;
  private Long equipmentId;
  private boolean calibrationExists;
  private EquipmentType equipmentEquipmentType;
  private boolean sentMail;
}
