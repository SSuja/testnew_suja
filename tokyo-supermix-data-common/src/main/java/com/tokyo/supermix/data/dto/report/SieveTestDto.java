package com.tokyo.supermix.data.dto.report;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;

public class SieveTestDto {
  private String code;
  private Date date;
  private Double finenessModulus;
  private Double panWeight;
  private Double totalWeight;
  private Status status;

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

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
