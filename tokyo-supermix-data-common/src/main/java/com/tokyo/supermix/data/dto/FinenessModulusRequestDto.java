package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;

public class FinenessModulusRequestDto {
	private Long id;
	@NotNull(message = "{FinenessModulusRequestDto.min.null}")
	private Double min;
	@NotNull(message = "{FinenessModulusRequestDto.max.null}")
	private Double max;
	@NotNull(message = "{FinenessModulusRequestDto.materialSubCategoryId.null}")
	private Long materialSubCategoryId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Long getMaterialSubCategoryId() {
		return materialSubCategoryId;
	}
	public void setMaterialSubCategoryId(Long materialSubCategoryId) {
		this.materialSubCategoryId = materialSubCategoryId;
	}
}
