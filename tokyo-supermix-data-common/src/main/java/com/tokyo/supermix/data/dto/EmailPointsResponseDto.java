package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailPointsResponseDto {
  private Long id;
  private String name;
  private boolean active;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private MaterialCategoryDto materialCategory;
  private TestDto test;
  private boolean adminLevelEmailConfiguration;
  private boolean schedule;
}
