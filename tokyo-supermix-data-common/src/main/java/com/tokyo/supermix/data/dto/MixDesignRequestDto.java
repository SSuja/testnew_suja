package com.tokyo.supermix.data.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MixDesignRequestDto {
	private String code;
	@NotNull(message = "{MixDesignRequestDto.grade.null}")
	@NotEmpty(message = "{MixDesignRequestDto.grade.empty}")
	private String grade;
	@NotNull(message = "{MixDesignRequestDto.date.null}")
	private Date date;
	@NotNull(message = "{MixDesignRequestDto.targetStrength.null}")
	@NotEmpty(message = "{MixDesignRequestDto.targetStrength.empty}")
	private String targetStrength;
	@NotNull(message = "{MixDesignRequestDto.waterCementRatio.null}")
	@NotEmpty(message = "{MixDesignRequestDto.waterCementRatio.empty}")
	private String waterCementRatio;
	@NotNull(message = "{MixDesignRequestDto.waterBinderRatio.null}")
	@NotEmpty(message = "{MixDesignRequestDto.waterBinderRatio.empty}")
	private String waterBinderRatio;
	private String plantCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTargetStrength() {
		return targetStrength;
	}

	public void setTargetStrength(String targetStrength) {
		this.targetStrength = targetStrength;
	}

	public String getWaterCementRatio() {
		return waterCementRatio;
	}

	public void setWaterCementRatio(String waterCementRatio) {
		this.waterCementRatio = waterCementRatio;
	}

	public String getWaterBinderRatio() {
		return waterBinderRatio;
	}

	public void setWaterBinderRatio(String waterBinderRatio) {
		this.waterBinderRatio = waterBinderRatio;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

}
