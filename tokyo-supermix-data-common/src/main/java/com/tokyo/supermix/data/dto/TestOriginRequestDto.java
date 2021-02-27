package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestOriginRequestDto {
  private Long testConfigureId;
  private Long materialCategoryId;
  private boolean coreTest;
  private Long materialSubCategoryId;
  private Long rawMaterialId;
}
