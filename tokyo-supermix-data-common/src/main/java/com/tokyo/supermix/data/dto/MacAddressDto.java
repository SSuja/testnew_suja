package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MacAddressDto {
  private Long id;
  @NotNull(message = "{macAddressDto.macAddress.null}")
  @NotEmpty(message = "{macAddressDto.macAddress.empty}")
  private String macAddress;
}
