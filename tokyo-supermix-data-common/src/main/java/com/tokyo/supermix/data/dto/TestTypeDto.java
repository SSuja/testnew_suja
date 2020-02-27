package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TestTypeDto {

  private Long id;
  @NotNull(message = "{testTypeDto.type.null}")
  @NotEmpty(message = "{testTypeDto.type.empty}")
  private String type;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


}
