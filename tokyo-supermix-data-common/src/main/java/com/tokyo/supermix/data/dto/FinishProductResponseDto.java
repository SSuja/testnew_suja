package com.tokyo.supermix.data.dto;

import java.util.Date;

public class FinishProductResponseDto {

	private Long id;
	private String mixDesignCode;
	private String projectName;
	private String projectCode;
	private Date date;
	private String pourName;
	private String pourId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMixDesignCode() {
		return mixDesignCode;
	}

	public void setMixDesignCode(String mixDesignCode) {
		this.mixDesignCode = mixDesignCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPourName() {
		return pourName;
	}

	public void setPourName(String pourName) {
		this.pourName = pourName;
	}

	public String getPourId() {
		return pourId;
	}

	public void setPourId(String pourId) {
		this.pourId = pourId;
	}

}
