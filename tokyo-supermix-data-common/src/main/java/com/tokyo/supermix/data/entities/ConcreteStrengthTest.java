package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "concrete_strength_test")
public class ConcreteStrengthTest implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long concreteAge;
  private Double strength;
  private Date date;
  private Double strengthGradeRatio;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @ManyToOne
  @JoinColumn(name = "mixDesignCode", nullable = false)
  private MixDesign mixDesign;

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
