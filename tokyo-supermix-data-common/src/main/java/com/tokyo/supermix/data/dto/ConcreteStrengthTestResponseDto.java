package com.tokyo.supermix.data.dto;

import java.util.Date;
import com.tokyo.supermix.data.enums.Status;

public class ConcreteStrengthTestResponseDto {
  private Long id;
  private String mixDesignCode;
  private double mixDesignTargetGrade;
  private Long concreteAge;
  private Double strength;
  private Date date;
  private Double strengthGradeRatio;
  private Status status;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

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

  public double getMixDesignTargetGrade() {
    return mixDesignTargetGrade;
  }

  public void setMixDesignTargetGrade(double mixDesignTargetGrade) {
    this.mixDesignTargetGrade = mixDesignTargetGrade;
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
