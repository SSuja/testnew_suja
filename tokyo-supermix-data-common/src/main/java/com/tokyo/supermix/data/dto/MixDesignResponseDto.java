package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class MixDesignResponseDto {
	private String code;
	private String grade;
	private Date date;
	private String targetStrength;
	private String waterCementRatio;
	private String waterBinderRatio;
	private String plantCode;
	private String plantName;
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
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	
	

}
