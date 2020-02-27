package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MaterialStateDto {
  private Long id;
  @NotNull(message = "{materialStateDto.materialState.null}")
  @NotEmpty(message = "{materialStateDto.materialState.empty}")
  private String materialState;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMaterialState() {
    return materialState;
  }

  public void setMaterialState(String materialState) {
    this.materialState = materialState;
  }

}
