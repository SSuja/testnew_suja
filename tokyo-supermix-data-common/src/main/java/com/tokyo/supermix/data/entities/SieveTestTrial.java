package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "sieve_test_trial")
public class SieveTestTrial implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double percentageRetained;
  private Double cummalativeRetained;
  private Double passing;
  @OneToOne
  @JoinColumn(name = "sieveTestId", nullable = false)
  private SieveTest sieveTest;
  @ManyToOne
  @JoinColumn(name = "sieveSizeId", nullable = false)
  private SieveSize sieveSize;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getPercentageRetained() {
    return percentageRetained;
  }

  public void setPercentageRetained(Double percentageRetained) {
    this.percentageRetained = percentageRetained;
  }

  public Double getCummalativeRetained() {
    return cummalativeRetained;
  }

  public void setCummalativeRetained(Double cummalativeRetained) {
    this.cummalativeRetained = cummalativeRetained;
  }

  public Double getPassing() {
    return passing;
  }

  public void setPassing(Double passing) {
    this.passing = passing;
  }

  public SieveTest getSieveTest() {
    return sieveTest;
  }

  public void setSieveTest(SieveTest sieveTest) {
    this.sieveTest = sieveTest;
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
