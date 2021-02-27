package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.FinishProductSampleType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductSampleRequestDto {
  private String code;
  private String finishProductCode;
  private String plantEquipmentSerialNo;
  @NotNull(message = "{finishProductSampleRequestDto.mixDesignCode.null}")
  @NotEmpty(message = "{finishProductSampleRequestDto.mixDesignCode.empty}")
  private String mixDesignCode;
  private Status status;
  private Date date;
  private String truckNo;
  private String projectCode;
  private Long pourId;
  private String workOrderNumber;
  private Long userId;
  private boolean sentMail;
  private FinishProductSampleType finishProductSampleType;
}
