package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.ConcreteStatus;

public class ConcreteTestStatusRequestDto {

	private Long id;
	private Long concreteTestTypeId;
	private Long finishProductSampleId;
	private ConcreteStatus concreteStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConcreteTestTypeId() {
		return concreteTestTypeId;
	}

	public void setConcreteTestTypeId(Long concreteTestTypeId) {
		this.concreteTestTypeId = concreteTestTypeId;
	}

	public Long getFinishProductSampleId() {
		return finishProductSampleId;
	}

	public void setFinishProductSampleId(Long finishProductSampleId) {
		this.finishProductSampleId = finishProductSampleId;
	}

	public ConcreteStatus getConcreteStatus() {
		return concreteStatus;
	}

	public void setConcreteStatus(ConcreteStatus concreteStatus) {
		this.concreteStatus = concreteStatus;
	}
}
