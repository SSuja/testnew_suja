package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "ratio_config_equation")
public class RatioConfigEquation {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String ratio;
  @ManyToOne
  @JoinColumn(name = "ratioConfigId", nullable = false)
  private RatioConfig ratioConfig;

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

  public RatioConfig getRatioConfig() {
    return ratioConfig;
  }

  public void setRatioConfig(RatioConfig ratioConfig) {
    this.ratioConfig = ratioConfig;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
