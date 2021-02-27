package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MixDesignProportionRequestDto {
  private Long id;
  @NotNull(message = "{mixDesignProportionRequestDto.quantity.null}")
  private Double quantity;
  private Long unitId;
  private String mixDesignCode;
  private Long rawMaterialId;
}
