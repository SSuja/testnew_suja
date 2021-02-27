package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoreTestConfigureDto {
  private Long id;
  private boolean coreTest;
  private Long materialCategoryId;
  private Long materialSubCategoryId;
  private Long rawMaterialId;
  private Long testConfigureId;
  private String materialSubCategoryName;
  private String rawMaterialName;
  private boolean applicableTest;
}
