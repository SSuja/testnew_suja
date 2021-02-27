package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.MainType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialSubCategoryResponseDto {
  private Long id;
  private String name;
  private String prefix;
  private Long materialCategoryId;
  private String materialCategoryName;
  private MainType materialCategoryMainType;
}
