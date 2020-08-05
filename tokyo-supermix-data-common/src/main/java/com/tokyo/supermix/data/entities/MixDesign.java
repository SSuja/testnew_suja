package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "mix_design")
public class MixDesign extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Double targetGrade;
  private Date date;
  private Double targetSlump;
  private Double waterCementRatio;
  private Double waterBinderRatio;
  private Status status;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = false)
  private MaterialCategory materialCategory;

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

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
