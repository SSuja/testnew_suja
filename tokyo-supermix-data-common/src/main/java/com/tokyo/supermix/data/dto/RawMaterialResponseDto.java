package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.MaterialType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RawMaterialResponseDto {
  private Long id;
  private String name;
  private MaterialStateDto materialState;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private String description;
  private String prefix;
  private boolean active;
  private PlantDto plant;
  private String erpCode;
  private Long subBusinessUnitId;
  private String subBusinessUnitName;
  private MaterialType materialType;
  private boolean sentMail;
}
