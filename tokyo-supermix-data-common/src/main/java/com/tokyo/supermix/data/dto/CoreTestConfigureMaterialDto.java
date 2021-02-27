package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoreTestConfigureMaterialDto {
  private Long coreTestConfigureId;
  private Long rawMaterialId;
  private String rawMaterialName;
  private boolean coreTest;
  private Long materialSubCategoryId;
}
