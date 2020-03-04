package com.tokyo.supermix.data.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FinishProductRequestDto {

	private Long id;
	@NotNull(message = "{finishProductRequestDto.mixDesignCode.null}")
	@NotEmpty(message = "{finishProductRequestDto.mixDesignCode.empty}")
	private String mixDesignCode;
	@NotNull(message = "{finishProductRequestDto.projectCode.null}")
	@NotEmpty(message = "{finishProductRequestDto.projectCode.empty}")
	private String projectCode;
	private Date date;
	private Long pourId;
	
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
	public Long getPourId() {
		return pourId;
	}
	public void setPourId(Long pourId) {
		this.pourId = pourId;
	}
	
	
}
