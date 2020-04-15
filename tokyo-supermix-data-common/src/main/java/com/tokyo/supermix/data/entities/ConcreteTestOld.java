package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "concrete_test_old")
public class ConcreteTestOld implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double slump;
  private String temperature;
  private Double waterContent;
  private Double slumpGradeRatio;
  private Double waterCementRatio;
  private Double waterBinderRatio;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @OneToOne
  @JoinColumn(name = "mixDesignCode", nullable = false)
  private MixDesign mixDesign;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Double getSlumpGradeRatio() {
    return slumpGradeRatio;
  }

  public void setSlumpGradeRatio(Double slumpGradeRatio) {
    this.slumpGradeRatio = slumpGradeRatio;
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

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public MixDesign getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesign mixDesign) {
    this.mixDesign = mixDesign;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
