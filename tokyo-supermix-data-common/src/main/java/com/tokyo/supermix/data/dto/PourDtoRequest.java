package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PourDtoRequest {
  private Long id;
  @NotNull(message = "{PourDto.name.null}")
  @NotEmpty(message = "{PourDto.name.empty}")
  private String name;
  private String description;
  @NotNull(message = "{PourDto.projectCode.null}")
  @NotEmpty(message = "{PourDto.projectCode.empty}")
  private String projectCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  private String projectName;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }

}
