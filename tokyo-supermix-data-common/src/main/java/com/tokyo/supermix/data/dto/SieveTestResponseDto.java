package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.data.enums.Status;

public class SieveTestResponseDto {
  private Long id;
  private Date date;
  private Double finenessModulus;
  private Status status;
  private User userId;
  private Plant plantCode;
  private IncomingSample incomingSampleCode;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
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
  public Plant getPlantCode() {
    return plantCode;
  }
  public void setPlantCode(Plant plantCode) {
    this.plantCode = plantCode;
  }
  public IncomingSample getIncomingSampleCode() {
    return incomingSampleCode;
  }
  public void setIncomingSampleCode(IncomingSample incomingSampleCode) {
    this.incomingSampleCode = incomingSampleCode;
  }
  
  
}
