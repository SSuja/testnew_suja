package com.tokyo.supermix.data.dto;

import java.sql.Date;

public class ConcreteTestResultRequestDto {
	private Long id;
	private Double result;
	private String temperature;
	private Double waterContent;
	private Date date;
	private Long age;
	private Long concreteTestId;
	private Long finishProductSampleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
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

	public Long getConcreteTestId() {
		return concreteTestId;
	}

	public void setConcreteTestId(Long concreteTestId) {
		this.concreteTestId = concreteTestId;
	}

	public Long getFinishProductSampleId() {
		return finishProductSampleId;
	}

	public void setFinishProductSampleId(Long finishProductSampleId) {
		this.finishProductSampleId = finishProductSampleId;
	}
}
