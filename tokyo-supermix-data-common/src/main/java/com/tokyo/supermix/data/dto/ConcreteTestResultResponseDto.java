package com.tokyo.supermix.data.dto;

import java.sql.Date;

import com.tokyo.supermix.data.enums.Status;

public class ConcreteTestResultResponseDto {
	private Long id;
	private String temperature;
	private Double waterContent;
	private Status status;
	private Date date;
	private Long age;
	private Double result;
	private ConcreteTestResponseDto ConcreteTest;
	private FinishProductSampleResponseDto FinishProductSample;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Double getWaterContent() {
		return waterContent;
	}

	public void setWaterContent(Double waterContent) {
		this.waterContent = waterContent;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public ConcreteTestResponseDto getConcreteTest() {
		return ConcreteTest;
	}

	public void setConcreteTest(ConcreteTestResponseDto concreteTest) {
		ConcreteTest = concreteTest;
	}

	public FinishProductSampleResponseDto getFinishProductSample() {
		return FinishProductSample;
	}

	public void setFinishProductSample(FinishProductSampleResponseDto finishProductSample) {
		FinishProductSample = finishProductSample;
	}
}
