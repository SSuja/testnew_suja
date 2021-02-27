package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.TestResultType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestConfigureResponseDto {
  private Long id;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private Long testId;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private String prefix;
  private MainType testType;
  private ReportFormat reportFormat;
  private String acceptedType;
  private boolean name;
  private String createdAt;
  private String updatedAt;
  private RawMaterialResponseDto rawMaterial;
  private MaterialCategoryDto materialCategory;
  private Long noOfTrial;
  private String dueDay;
  private TestResultType testResultType;
}
