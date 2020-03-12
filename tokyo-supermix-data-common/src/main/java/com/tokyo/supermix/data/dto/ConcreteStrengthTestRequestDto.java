package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Status;

public class ConcreteStrengthTestRequestDto {
  private Long id;
  @NotNull(message = "{concreteStrengthTestRequestDto.mixDesignCode.null}")
  @NotEmpty(message = "{concreteStrengthTestRequestDto.mixDesignCode.empty}")
  private String mixDesignCode;
  @NotNull(message = "{concreteStrengthTestRequestDto.concreteAge.null}")
  private Long concreteAge;
  @NotNull(message = "{concreteStrengthTestRequestDto.strength.null}")
  private Double strength;
  private Double strengthGradeRatio;
  private Status status;

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

  public Long getConcreteAge() {
    return concreteAge;
  }

  public void setConcreteAge(Long concreteAge) {
    this.concreteAge = concreteAge;
  }

  public Double getStrength() {
    return strength;
  }

  public void setStrength(Double strength) {
    this.strength = strength;
  }

  public Double getStrengthGradeRatio() {
    return strengthGradeRatio;
  }

  public void setStrengthGradeRatio(Double strengthGradeRatio) {
    this.strengthGradeRatio = strengthGradeRatio;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }



}
