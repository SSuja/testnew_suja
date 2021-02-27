package com.tokyo.supermix.data.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectRequestDto {
  private String code;
  @NotNull(message = "{projectRequestDto.name.null}")
  @NotEmpty(message = "{projectRequestDto.name.empty}")
  private String name;
  @NotNull(message = "{projectRequestDto.contactNumber.null}")
  @NotEmpty(message = "{projectRequestDto.contactNumber.empty}")
  private String contactNumber;
  private String contactPerson;
  private Date startDate;
  private Long customerId;
  @NotNull(message = "{projectRequestDto.plantCode.null}")
  @NotEmpty(message = "{projectRequestDto.plantCode.empty}")
  private String plantCode;
  private boolean sentMail;
}
