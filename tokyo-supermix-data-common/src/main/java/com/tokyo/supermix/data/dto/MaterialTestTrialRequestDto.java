package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialTestTrialRequestDto {
  private String code;
  @NotNull(message = "{materialTestTrialRequestDto.trialNo.null}")
  private Long trialNo;
  @NotNull(message = "{materialTestTrialRequestDto.materialTestCode.null}")
  @NotEmpty(message = "{materialTestTrialRequestDto.materialTestCode.empty}")
  private String materialTestCode;
}
