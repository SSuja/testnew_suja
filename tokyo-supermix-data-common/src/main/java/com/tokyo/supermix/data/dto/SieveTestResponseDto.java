package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.data.enums.Status;

public class SieveTestResponseDto {
  private String code;
  private Date date;
  private Double finenessModulus;
  private Double panWeight;
  private Double totalWeight;
  private Status status;
  private User userId;
  private Plant plant;
  private IncomingSampleResponseDto incomingSample;


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

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public IncomingSampleResponseDto getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSampleResponseDto incomingSample) {
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


}
