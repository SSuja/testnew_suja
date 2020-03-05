package com.tokyo.supermix.data.dto;

import java.util.Date;

public class FinishProductResponseDto {

	private Long id;
	private String mixDesignCode;
	private String projectCode;
	private String projectName;
	private Date date;
	private Long pourId;
	private String pourName;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getPourId() {
		return pourId;
	}

	public void setPourId(Long pourId) {
		this.pourId = pourId;
	}

	public String getPourName() {
		return pourName;
	}

	public void setPourName(String pourName) {
		this.pourName = pourName;
	}

}
