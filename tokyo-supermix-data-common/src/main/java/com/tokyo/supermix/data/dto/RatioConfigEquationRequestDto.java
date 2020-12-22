package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RatioConfigEquationRequestDto {

  private Long id;
  @NotNull(message = "{ratioConfigDto.ratio.null}")
  @NotEmpty(message = "{ratioConfigDto.ratio.empty}")
  private String ratio;
  private Long ratioConfigId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRatio() {
    return ratio;
  }

  public void setRatio(String ratio) {
    this.ratio = ratio;
  }

  public Long getRatioConfigId() {
    return ratioConfigId;
  }

  public void setRatioConfigId(Long ratioConfigId) {
    this.ratioConfigId = ratioConfigId;
  }
}
