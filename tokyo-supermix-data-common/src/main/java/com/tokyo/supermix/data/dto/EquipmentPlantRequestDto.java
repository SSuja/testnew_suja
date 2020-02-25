package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EquipmentPlantRequestDto {
	@NotNull(message = "{euipmentPlantDto.serialNo.null}")
	@NotEmpty(message = "{euipmentPlantDto.serialNo.empty}")
	private String serialNo;
	@NotNull(message = "{euipmentPlantDto.brandName.null}")
	@NotEmpty(message = "{euipmentPlantDto.brandName.empty}")
	private String brandName;
	private String modelName;
	private String description;
	@NotNull(message = "{euipmentPlantDto.plantCode.null}")
	@NotEmpty(message = "{euipmentPlantDto.plantCode.empty}")
	private String plantCode;
	private Long equipmentId;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public Long getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}

}
