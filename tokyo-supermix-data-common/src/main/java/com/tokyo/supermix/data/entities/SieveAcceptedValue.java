package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "sieve_accepted_value")
public class SieveAcceptedValue implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long minimumValue;
  private Long maximumValue;
  @OneToOne
  @JoinColumn(name = "sieveSizeId", nullable = false)
  private SieveSize sieveSize;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMinimumValue() {
    return minimumValue;
  }

  public void setMinimumValue(Long minimumValue) {
    this.minimumValue = minimumValue;
  }

  public Long getMaximumValue() {
    return maximumValue;
  }

  public void setMaximumValue(Long maximumValue) {
    this.maximumValue = maximumValue;
  }

  public SieveSize getSieveSize() {
    return sieveSize;
  }

  public void setSieveSize(SieveSize sieveSize) {
    this.sieveSize = sieveSize;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
