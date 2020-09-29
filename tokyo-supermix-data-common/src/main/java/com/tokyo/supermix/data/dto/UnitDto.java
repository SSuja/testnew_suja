package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UnitDto {

  private Long id;
  @NotNull(message = "{unitDto.unit.null}")
  @NotEmpty(message = "{unitDto.unit.empty}")
  private String unit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

}
