package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ConcreteTestRequestDto {
	private Long id;
	@NotNull(message = "{concreteTestRequestDto.finishProductSampleId.null}")
	@NotEmpty(message = "{concreteTestRequestDto.finishProductSampleId.empty}")
	private Long finishProductSampleId;
	@NotNull(message = "{concreteTestRequestDto.slump.null}")
	private Double slump;
	@NotNull(message = "{concreteTestRequestDto.temperature.null}")
	@NotEmpty(message = "{concreteTestRequestDto.temperature.empty}")
	private String temperature;
	@NotNull(message = "{concreteTestRequestDto.waterContent.null}")
	private Double waterContent;

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

	public Double getSlump() {
		return slump;
	}

	public void setSlump(Double slump) {
		this.slump = slump;
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
}
