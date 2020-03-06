package com.tokyo.supermix.data.dto;

public class AdmixtureAcceptedValueRequestDto {
	private Long id;
	private Double minValue;
	private Double maxValue;
	private Long testId;
	private Long parameterId;
	private Long unitId;
	private Long rawMaterialId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Long getTestId() {
		return testId;
	}
	public void setTestId(Long testId) {
		this.testId = testId;
	}
	public Long getParameterId() {
		return parameterId;
	}
	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public Long getRawMaterialId() {
		return rawMaterialId;
	}
	public void setRawMaterialId(Long rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}
	
	
	  
}
