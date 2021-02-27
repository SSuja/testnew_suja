package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessSampleRequestDto {
  private String code;
  @NotNull(message = "{processSampleRequestDto.incomingSampleCode.null}")
  @NotEmpty(message = "{processSampleRequestDto.incomingSampleCode.empty}")
  private String incomingSampleCode;
  @NotNull(message = "{processSampleRequestDto.quantity.null}")
  private Long quantity;
  @NotNull(message = "{processSampleRequestDto.unitId.null}")
  private Long unitId;
}
