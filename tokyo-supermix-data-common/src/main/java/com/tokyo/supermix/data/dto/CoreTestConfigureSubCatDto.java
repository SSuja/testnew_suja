package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoreTestConfigureSubCatDto {
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private Long materialCategoryId;
  private boolean coretest;
  private List<CoreTestConfigureMaterialDto> coreTestConfigureMaterialDtos;
}
