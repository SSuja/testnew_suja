package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConcreteMixerRequestDto {
  private Long id;
  @NotNull(message = "{concreteMixerRequestDto.name.null}")
  @NotEmpty(message = "{concreteMixerRequestDto.name.empty}")
  private String name;
  @NotNull(message = "{concreteMixerRequestDto.plantCode.null}")
  @NotEmpty(message = "{concreteMixerRequestDto.plantCode.empty}")
  private String plantCode;

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

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

}
