package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SieveTestRequestDto {
  @NotNull(message = "{sieveTestRequestDto.code.null}")
  @NotEmpty(message = "{sieveTestRequestDto.code.empty}")
  private String code;
  @NotNull(message = "{sieveTestRequestDto.date.null}")
  private Date date;
  private Double finenessModulus;
  private String status;
  @NotNull(message = "{sieveTestRequestDto.userId.null}")
  private Long userId;
  @NotNull(message = "{sieveTestRequestDto.plantCode.null}")
  @NotEmpty(message = "{sieveTestRequestDto.plantCode.empty}")
  private String plantCode;
  @NotNull(message = "{sieveTestRequestDto.incomingSampleCode.null}")
  @NotEmpty(message = "{sieveTestRequestDto.incomingSampleCode.empty}")
  private String incomingSampleCode;
  private Double panWeight;
  private Double totalWeight;

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
