package com.tokyo.supermix.data.dto;

import javax.annotation.Nullable;
import com.tokyo.supermix.data.enums.TestResultType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestConfigureResDto {
  private Long testConfigureId;
  private Long materialSubCategoryId;
  private String materialSubCategoryName;
  private String testName;
  private Long materialCategoryId;
  private String materialCategoryName;
  @Nullable
  private Long rawMaterialId;
  @Nullable
  private String rawMaterialName;
  private String acceptedType;
  private String testType;
  private String dueDay;
  private TestResultType testResultType;
}
