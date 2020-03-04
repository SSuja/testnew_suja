package com.tokyo.supermix.data.dto;

import java.util.Date;

public class ProcessSampleResponseDto {
  private String code;
  private String incomingSampleCode;
  private Long rawMaterialId;
  private Long quantity;
  private String rawMaterialName;
  private Date incomingSampleDate;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getIncomingSampleCode() {
    return incomingSampleCode;
  }

  public void setIncomingSampleCode(String incomingSampleCode) {
    this.incomingSampleCode = incomingSampleCode;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }

  public Date getIncomingSampleDate() {
    return incomingSampleDate;
  }

  public void setIncomingSampleDate(Date incomingSampleDate) {
    this.incomingSampleDate = incomingSampleDate;
  }

}
