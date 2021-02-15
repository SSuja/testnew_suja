package com.tokyo.supermix.data.dto;

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
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }
  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
  }
  public Long getMaterialCategoryId() {
    return materialCategoryId;
  }
  public void setMaterialCategoryId(Long materialCategoryId) {
    this.materialCategoryId = materialCategoryId;
  }
  public Long getTestId() {
    return testId;
  }
  public void setTestId(Long testId) {
    this.testId = testId;
  }
  public boolean isAdminLevelEmailConfiguration() {
    return adminLevelEmailConfiguration;
  }
  public void setAdminLevelEmailConfiguration(boolean adminLevelEmailConfiguration) {
    this.adminLevelEmailConfiguration = adminLevelEmailConfiguration;
  }
public boolean isSchedule() {
	return schedule;
}
public void setSchedule(boolean schedule) {
	this.schedule = schedule;
}
public Long getTestConfigureId() {
  return testConfigureId;
}
public void setTestConfigureId(Long testConfigureId) {
  this.testConfigureId = testConfigureId;
} 
}