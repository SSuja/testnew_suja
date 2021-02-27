package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailPointsRequestDto {
  private Long id;
  private String name;
  private boolean active;
  private Long materialSubCategoryId;
  private Long materialCategoryId;
  private Long testId;
  private Long testConfigureId;
  private boolean adminLevelEmailConfiguration;
  private boolean schedule;
}
