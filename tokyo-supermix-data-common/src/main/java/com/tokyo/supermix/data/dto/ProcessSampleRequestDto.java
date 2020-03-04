package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProcessSampleRequestDto {
  @NotNull(message = "{processSampleRequestDto.code.null}")
  @NotEmpty(message = "{processSampleRequestDto.code.empty}")
  private String code;
  @NotNull(message = "{processSampleRequestDto.incomingSampleCode.null}")
  @NotEmpty(message = "{processSampleRequestDto.incomingSampleCode.empty}")
  private String incomingSampleCode;
  private Long rawMaterialId;
  @NotNull(message = "{processSampleRequestDto.quantity.null}")
  private Long quantity;

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
}
