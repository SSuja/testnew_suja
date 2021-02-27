package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoreTestConfigureResponseDto {
  public Long testConfigureId;
  private Long materialCategoryId;
  private String materialCategoryName;
  private boolean coreTest;
  public List<CoreTestConfigureSubCatDto> coreTestConfigureSubCatDto;
}
