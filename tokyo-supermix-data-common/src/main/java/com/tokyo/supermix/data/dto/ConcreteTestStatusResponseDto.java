package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.ConcreteStatus;

public class ConcreteTestStatusResponseDto {
	private Long id;
	private ConcreteStatus concreteStatus;
	private ConcreteTestTypeDto ConcreteTestType;
	private FinishProductSampleResponseDto finishProductSample;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConcreteTestTypeDto getConcreteTestType() {
		return ConcreteTestType;
	}

	public void setConcreteTestType(ConcreteTestTypeDto concreteTestType) {
		ConcreteTestType = concreteTestType;
	}

	public FinishProductSampleResponseDto getFinishProductSample() {
		return finishProductSample;
	}

	public void setFinishProductSample(FinishProductSampleResponseDto finishProductSample) {
		this.finishProductSample = finishProductSample;
	}

	public ConcreteStatus getConcreteStatus() {
		return concreteStatus;
	}

	public void setConcreteStatus(ConcreteStatus concreteStatus) {
		this.concreteStatus = concreteStatus;
	}
}
