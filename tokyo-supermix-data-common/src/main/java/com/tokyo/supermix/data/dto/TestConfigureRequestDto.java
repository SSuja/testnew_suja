package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.TestResultType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestConfigureRequestDto {
  private Long id;
  private Long testId;
  private MainType testType;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  @NotNull(message = "{testConfigureRequestDto.prefix.null}")
  @NotEmpty(message = "{testConfigureRequestDto.prefix.empty}")
  @Pattern(regexp = "^[a-zA-Z0-9\\s_.-]+$*",
      message = "{testConfigureRequestDto.prefix.specialcharacter}")
  private String prefix;
  private Long materialCategoryId;
  private Long materialSubCategoryId;
  private Long rawMaterialId;
  private boolean active;
  private boolean adminLevelEmailConfiguration;
  private ReportFormat reportFormat;
  private AcceptedType acceptedType;
  private boolean name;
  private Long noOfTrial;
  private String dueDay;
  private TestResultType testResultType;
}
