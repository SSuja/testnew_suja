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
  private Long min;
  private Long max;
  @OneToOne
  @JoinColumn(name = "sieveSizeId", nullable = false)
  private SieveSize sieveSize;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMin() {
    return min;
  }

  public void setMin(Long min) {
    this.min = min;
  }

  public Long getMax() {
    return max;
  }

  public void setMax(Long max) {
    this.max = max;
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
