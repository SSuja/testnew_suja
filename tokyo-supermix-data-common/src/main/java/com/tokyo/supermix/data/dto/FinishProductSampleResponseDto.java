package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.FinishProductSampleType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductSampleResponseDto {
  private String code;
  private String finishProductCode;
  private PlantEquipmentResponseDto plantEquipment;
  private MixDesignResponseDto mixDesign;
  private Status status;
  private Date date;
  private String createdAt;
  private String updatedAt;
  private String truckNo;
  private ProjectResponseDto project;
  private PourDtoResponse pour;
  private String workOrderNumber;
  private boolean sentMail;
  private FinishProductSampleType finishProductSampleType;
}
