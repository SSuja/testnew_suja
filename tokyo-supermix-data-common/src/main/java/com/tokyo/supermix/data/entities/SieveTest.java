package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "sieve_test")
public class SieveTest implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Date date;
  private Double finenessModulus;
  private Double panWeight;
  private Double totalWeight;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;
  @ManyToOne
  @JoinColumn(name = "incomingSampleCode", nullable = false)
  private IncomingSample incomingSample;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getFinenessModulus() {
    return finenessModulus;
  }

  public void setFinenessModulus(Double finenessModulus) {
    this.finenessModulus = finenessModulus;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public IncomingSample getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSample incomingSample) {
    this.incomingSample = incomingSample;
  }

  public Double getPanWeight() {
    return panWeight;
  }

  public void setPanWeight(Double panWeight) {
    this.panWeight = panWeight;
  }

  public Double getTotalWeight() {
    return totalWeight;
  }

  public void setTotalWeight(Double totalWeight) {
    this.totalWeight = totalWeight;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
