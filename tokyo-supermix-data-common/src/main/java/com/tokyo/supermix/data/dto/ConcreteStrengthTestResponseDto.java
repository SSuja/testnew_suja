package com.tokyo.supermix.data.dto;

import java.util.Date;
import com.tokyo.supermix.data.enums.Status;

public class ConcreteStrengthTestResponseDto {
	private Long id;
	private Long concreteAge;
	private Double strength;
	private Date date;
	private Double strengthGradeRatio;
	private Status status;
	private FinishProductSampleResponseDto finishProductSampleResponseDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConcreteAge() {
		return concreteAge;
	}

	public void setConcreteAge(Long concreteAge) {
		this.concreteAge = concreteAge;
	}

	public Double getStrength() {
		return strength;
	}

	public void setStrength(Double strength) {
		this.strength = strength;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getStrengthGradeRatio() {
		return strengthGradeRatio;
	}

	public void setStrengthGradeRatio(Double strengthGradeRatio) {
		this.strengthGradeRatio = strengthGradeRatio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public FinishProductSampleResponseDto getFinishProductSampleResponseDto() {
		return finishProductSampleResponseDto;
	}

	public void setFinishProductSampleResponseDto(FinishProductSampleResponseDto finishProductSampleResponseDto) {
		this.finishProductSampleResponseDto = finishProductSampleResponseDto;
	}
}
