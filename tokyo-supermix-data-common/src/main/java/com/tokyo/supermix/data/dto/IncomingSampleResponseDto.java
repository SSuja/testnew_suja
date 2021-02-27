package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IncomingSampleResponseDto {
  private String code;
  private String vehicleNo;
  private String createdAt;
  private String updatedAt;
  private Status status;
  private RawMaterialResponseDto rawMaterial;
  private PlantDto plant;
  private SupplierResponseDto supplier;
  private RawMaterialSampleType rawMaterialSampleType;
  private Date date;
  private boolean sentMail;
}
