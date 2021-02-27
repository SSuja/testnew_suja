package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlantDto {
  @NotNull(message = "{plantDto.id.null}")
  private String code;
  @NotNull(message = "{plantDto.name.null}")
  @NotEmpty(message = "{plantDto.name.empty}")
  private String name;
  private String address;
  private String phoneNumber;
  private String description;
  private String faxNumber;
  private Long subBusinessUnitId;
  private String subBusinessUnitName;
  private boolean sentMail;
}
