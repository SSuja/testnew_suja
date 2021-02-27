package com.tokyo.supermix.data.dto;

import java.util.List;

import com.tokyo.supermix.data.dto.report.MaterialAcceptedValueDto;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.MainType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestConfigureDto {
  private Long id;
  private MainType testType;
  private String testName;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private AcceptedType acceptedType;
  private String prefix;
  private List<AcceptedValuesDto> acceptedValue;
  private List<AccepetedValueDto> materialAcceptedValue;
  private List<TestParametersDto> testparameters;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private RawMaterialResponseDto rawMaterial;
  private List<TestEquationResponseDto> testEquations;
  private List<MaterialAcceptedValueDto> rawMaterialDto;
  private Long noOfTrial;
  private String dueDay;
}
