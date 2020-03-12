package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class MaterialTestResponseDto {

	private Long id;
	private Date date;
	private Long noOfTrial;
	private Double average;
	private String status;
	private String testLevel;
	private String incomingSampleCode;
	private Long testId;
	private String testName;
	private Long materialStateId;
	private String materialState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getNoOfTrial() {
		return noOfTrial;
	}

	public void setNoOfTrial(Long noOfTrial) {
		this.noOfTrial = noOfTrial;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTestLevel() {
		return testLevel;
	}

	public void setTestLevel(String testLevel) {
		this.testLevel = testLevel;
	}

	public String getIncomingSampleCode() {
		return incomingSampleCode;
	}

	public void setIncomingSampleCode(String incomingSampleCode) {
		this.incomingSampleCode = incomingSampleCode;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Long getMaterialStateId() {
		return materialStateId;
	}

	public void setMaterialStateId(Long materialStateId) {
		this.materialStateId = materialStateId;
	}

	public String getMaterialState() {
		return materialState;
	}

	public void setMaterialState(String materialState) {
		this.materialState = materialState;
	}

}
