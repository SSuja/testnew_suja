package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;

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
  private boolean active;
  private boolean adminLevelEmailConfiguration;
  private ReportFormat reportFormat;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTestId() {
    return testId;
  }

  public void setTestId(Long testId) {
    this.testId = testId;
  }

  public MainType getTestType() {
    return testType;
  }

  public void setTestType(MainType testType) {
    this.testType = testType;
  }

  public boolean isCoreTest() {
    return coreTest;
  }

  public void setCoreTest(boolean coreTest) {
    this.coreTest = coreTest;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTestProcedure() {
    return testProcedure;
  }

  public void setTestProcedure(String testProcedure) {
    this.testProcedure = testProcedure;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public Long getMaterialCategoryId() {
    return materialCategoryId;
  }

  public void setMaterialCategoryId(Long materialCategoryId) {
    this.materialCategoryId = materialCategoryId;
  }

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isAdminLevelEmailConfiguration() {
    return adminLevelEmailConfiguration;
  }

  public void setAdminLevelEmailConfiguration(boolean adminLevelEmailConfiguration) {
    this.adminLevelEmailConfiguration = adminLevelEmailConfiguration;
  }

  public ReportFormat getReportFormat() {
    return reportFormat;
  }

  public void setReportFormat(ReportFormat reportFormat) {
    this.reportFormat = reportFormat;
  }
}
