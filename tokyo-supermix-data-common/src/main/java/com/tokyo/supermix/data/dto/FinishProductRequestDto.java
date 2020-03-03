package com.tokyo.supermix.data.dto;

import java.util.Date;

public class FinishProductRequestDto {

	private Long id;
	private String mixDesignCode;
	private String projectCode;
	private Date date;
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
	public String getPourId() {
		return pourId;
	}
	public void setPourId(String pourId) {
		this.pourId = pourId;
	}
	
	
}
