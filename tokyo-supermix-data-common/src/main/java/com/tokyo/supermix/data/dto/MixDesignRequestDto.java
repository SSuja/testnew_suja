package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotNull;

public class MixDesignRequestDto {
  private String code;
  private Double targetGrade;
  private Double actualGrade;
  @NotNull(message = "{MixDesignRequestDto.date.null}")
  private Date date;
  private Double targetSlump;
  private Double waterCementRatio;
  private Double waterBinderRatio;
  private String plantCode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Double getTargetGrade() {
    return targetGrade;
  }

  public void setTargetGrade(Double targetGrade) {
    this.targetGrade = targetGrade;
  }

  public Double getActualGrade() {
    return actualGrade;
  }

  public void setActualGrade(Double actualGrade) {
    this.actualGrade = actualGrade;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getTargetSlump() {
    return targetSlump;
  }

  public void setTargetSlump(Double targetSlump) {
    this.targetSlump = targetSlump;
  }

  public Double getWaterCementRatio() {
    return waterCementRatio;
  }

  public void setWaterCementRatio(Double waterCementRatio) {
    this.waterCementRatio = waterCementRatio;
  }

  public Double getWaterBinderRatio() {
    return waterBinderRatio;
  }

  public void setWaterBinderRatio(Double waterBinderRatio) {
    this.waterBinderRatio = waterBinderRatio;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

}
