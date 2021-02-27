package com.tokyo.supermix.data.dto;

import java.sql.Date;

import com.tokyo.supermix.data.enums.CalibrationType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlantEquipmentCalibrationResponseDto {
  private Long id;
  private Date calibratedDate;
  private Date dueDate;
  private CalibrationType calibrationType;
  private String description;
  private Status status;
  private String accuracy;
  private Long noOfDays;
  private SupplierResponseDto supplier;
  private EmployeeResponseDto employee;
  private PlantEquipmentResponseDto plantEquipment;
  private boolean sentMail;
  private UnitDto unit;
}
