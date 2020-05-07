package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotNull;

public class CubeTestFindingRequestDto {
	private Long id;
	@NotNull(message = "{cubeTestFindingRequestDto.cubeNo.null}")
	private Long cubeNo;
	@NotNull(message = "{cubeTestFindingRequestDto.age.null}")
	private Long age;
	@NotNull(message = "{cubeTestFindingRequestDto.value.null}")
	private Double value;
	private Long finishProductSampleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCubeNo() {
		return cubeNo;
	}

	public void setCubeNo(Long cubeNo) {
		this.cubeNo = cubeNo;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Long getFinishProductSampleId() {
		return finishProductSampleId;
	}

	public void setFinishProductSampleId(Long finishProductSampleId) {
		this.finishProductSampleId = finishProductSampleId;
	}
}
