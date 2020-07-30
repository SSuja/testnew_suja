package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProcessSampleRequestDto {
  private String code;
  @NotNull(message = "{processSampleRequestDto.incomingSampleCode.null}")
  @NotEmpty(message = "{processSampleRequestDto.incomingSampleCode.empty}")
  private String incomingSampleCode;
  @NotNull(message = "{processSampleRequestDto.quantity.null}")
  private Long quantity;
  @NotNull(message = "{processSampleRequestDto.unitId.null}")
  private Long unitId;

  public Long getUnitId() {
    return unitId;
  }

  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }

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

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
