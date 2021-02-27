package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.CalibrationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlantEquipmentCalibrationRequestDto {
  private Long id;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.calibratedDate.null}")
  private Date calibratedDate;
  private CalibrationType calibrationType;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.userId.null}")
  private Long userId;
  private String description;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.plantEquipmentSerialNo.null}")
  @NotEmpty(message = "{plantEquipmentCalibrationRequestDto.plantEquipmentSerialNo.empty}")
  private String plantEquipmentSerialNo;
  private Long supplierId;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.status.null}")
  @NotEmpty(message = "{plantEquipmentCalibrationRequestDto.status.empty}")
  private String status;
  private Long employeeId;
  private String accuracy;
  @NotNull(message = "{plantEquipmentCalibrationRequestDto.noOfDays.null}")
  private Long noOfDays;
  private boolean sentMail;
  private Long unitId;
}
