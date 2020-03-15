package com.tokyo.supermix.data.dto;

import java.sql.Date;

import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestLevel;

public class MaterialTestRequestDto {

	private String code;
	private Date date;
	private Long noOfTrial;
	private Double average;
	private Status status;
	private TestLevel testLevel;
	private String incomingSampleCode;
	private Long testId;
	private Long materialStateId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TestLevel getTestLevel() {
		return testLevel;
	}

	public void setTestLevel(TestLevel testLevel) {
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

	public Long getMaterialStateId() {
		return materialStateId;
	}

	public void setMaterialStateId(Long materialStateId) {
		this.materialStateId = materialStateId;
	}

}
