package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class SieveTestRequestDto {
  private Long id;
  private Date date;
  private Double finenessModulus;
  private String status;
  private Long userId;
  private String plantCode;
  private String incomingSampleCode;

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public String getIncomingSampleCode() {
    return incomingSampleCode;
  }

  public void setIncomingSampleCode(String incomingSampleCode) {
    this.incomingSampleCode = incomingSampleCode;
  }
}
