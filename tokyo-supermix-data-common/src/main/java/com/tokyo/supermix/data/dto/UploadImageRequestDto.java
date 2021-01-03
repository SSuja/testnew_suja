package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UploadImageRequestDto {

  private Long id;
  @NotNull(message = "{uploadDto.testImage.null}")
  @NotEmpty(message = "{uploadDto.testImage.empty}")
  private String testImage;
  private String name;
  private String materialTestCode;
  private String finishProductTestCode;
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTestImage() {
    return testImage;
  }

  public void setTestImage(String testImage) {
    this.testImage = testImage;
  }

  public String getMaterialTestCode() {
    return materialTestCode;
  }

  public void setMaterialTestCode(String materialTestCode) {
    this.materialTestCode = materialTestCode;
  }

  public String getFinishProductTestCode() {
    return finishProductTestCode;
  }

  public void setFinishProductTestCode(String finishProductTestCode) {
    this.finishProductTestCode = finishProductTestCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
