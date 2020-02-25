package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EquipmentPlantCalibrationRequestDto {
	private Long id;
	private Date calibratedDate;
	private Date dueDate;
	@NotNull(message = "{equipmentPlantCalibrationDto.calibratedBy.null}")
	@NotEmpty(message = "{equipmentPlantCalibrationDto.calibratedBy.empty}")
	private String calibratedBy;
	@NotNull(message = "{equipmentPlantCalibrationDto.testerName.null}")
    @NotEmpty(message = "{equipmentPlantCalibrationDto.testerName.empty}")
	private String testerName;
	private String description;
	private Long equipmentPlantId;
	private Long supplierId;
	@NotNull(message = "{equipmentPlantCalibrationDto.status.null}")
    @NotEmpty(message = "{equipmentPlantCalibrationDto.status.empty}")
	private String status;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCalibratedDate() {
		return calibratedDate;
	}

	public void setCalibratedDate(Date calibratedDate) {
		this.calibratedDate = calibratedDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getCalibratedBy() {
		return calibratedBy;
	}

	public void setCalibratedBy(String calibratedBy) {
		this.calibratedBy = calibratedBy;
	}

	public String getTesterName() {
		return testerName;
	}

	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEquipmentPlantId() {
		return equipmentPlantId;
	}

	public void setEquipmentPlantId(Long equipmentPlantId) {
		this.equipmentPlantId = equipmentPlantId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
