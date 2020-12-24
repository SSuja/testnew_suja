package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "mix_design_ratio_config")
public class MixDesignRatioConfig {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "mixDesignCode", nullable = false)
  private MixDesign mixDesign;
  @ManyToOne
  @JoinColumn(name = "ratioConfigId", nullable = false)
  private RatioConfig ratioConfig;
  private Double value;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MixDesign getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesign mixDesign) {
    this.mixDesign = mixDesign;
  }

  public RatioConfig getRatioConfig() {
    return ratioConfig;
  }

  public void setRatioConfig(RatioConfig ratioConfig) {
    this.ratioConfig = ratioConfig;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
