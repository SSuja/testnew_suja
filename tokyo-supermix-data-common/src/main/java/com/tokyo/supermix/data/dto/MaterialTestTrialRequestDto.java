package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MaterialTestTrialRequestDto {
  private String code;
  @NotNull(message = "{materialTestTrialRequestDto.trialNo.null}")
  private Long trialNo;
  @NotNull(message = "{materialTestTrialRequestDto.materialTestCode.null}")
  @NotEmpty(message = "{materialTestTrialRequestDto.materialTestCode.empty}")
  private String materialTestCode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public String getMaterialTestCode() {
    return materialTestCode;
  }

  public void setMaterialTestCode(String materialTestCode) {
    this.materialTestCode = materialTestCode;
  }
}
