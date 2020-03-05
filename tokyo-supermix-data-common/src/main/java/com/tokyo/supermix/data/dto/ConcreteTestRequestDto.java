package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Status;

public class ConcreteTestRequestDto {
  private Long id;
  @NotNull(message = "{concreteTestRequestDto.mixDesignCode.null}")
  @NotEmpty(message = "{concreteTestRequestDto.mixDesignCode.empty}")
  private String mixDesignCode;
  @NotNull(message = "{concreteTestRequestDto.slump.null}")
  private Double slump;
  @NotNull(message = "{concreteTestRequestDto.temperature.null}")
  @NotEmpty(message = "{concreteTestRequestDto.temperature.empty}")
  private String temperature;
  @NotNull(message = "{concreteTestRequestDto.waterContent.null}")
  private Double waterContent;
  private Status status;
  @NotNull(message = "{concreteTestRequestDto.plantCode.null}")
  @NotEmpty(message = "{concreteTestRequestDto.plantCode.empty}")
  private String plantCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMixDesignCode() {
    return mixDesignCode;
  }

  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
  }

  public Double getSlump() {
    return slump;
  }

  public void setSlump(Double slump) {
    this.slump = slump;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public Double getWaterContent() {
    return waterContent;
  }

  public void setWaterContent(Double waterContent) {
    this.waterContent = waterContent;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }
}
