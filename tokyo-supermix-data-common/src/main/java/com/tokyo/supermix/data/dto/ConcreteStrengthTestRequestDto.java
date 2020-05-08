package com.tokyo.supermix.data.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.Status;

public class ConcreteStrengthTestRequestDto {
	private Long id;
	private Long finishProductSampleId;
	@NotNull(message = "{concreteStrengthTestRequestDto.concreteAge.null}")
	private Long concreteAge;
	private Date date;
	private Double strength = 0.0;
	private Double strengthGradeRatio;
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFinishProductSampleId() {
		return finishProductSampleId;
	}

	public void setFinishProductSampleId(Long finishProductSampleId) {
		this.finishProductSampleId = finishProductSampleId;
	}

	public Long getConcreteAge() {
		return concreteAge;
	}

	public void setConcreteAge(Long concreteAge) {
		this.concreteAge = concreteAge;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getStrength() {
		return strength;
	}

	public void setStrength(Double strength) {
		this.strength = strength;
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
}
