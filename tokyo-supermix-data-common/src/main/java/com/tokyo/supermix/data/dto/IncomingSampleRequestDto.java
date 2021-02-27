package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IncomingSampleRequestDto {
  private String code;
  private String vehicleNo;
  private Status status;
  private Long rawMaterialId;
  @NotNull(message = "{incomingSampleRequestDto.plantCode.null}")
  @NotEmpty(message = "{incomingSampleRequestDto.plantCode.empty}")
  private String plantCode;
  private Long supplierId;
  private RawMaterialSampleType rawMaterialSampleType;
  private Long userId;
  private boolean sentMail;
  private Date date;
}
