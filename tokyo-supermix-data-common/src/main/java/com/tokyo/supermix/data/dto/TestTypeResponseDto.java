package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestTypeResponseDto {
  private Long id;
  private String type;
  private String classification;
  private MaterialSubCategoryResponseDto materialSubCategory;
}
