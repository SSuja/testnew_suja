package com.tokyo.supermix.data.dto;

public class MixDesignParameterResultRequestDto {

	private Long id;
	private String mixDesignCode;
	private Long mixDesignParameterId;
	private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMixDesignCode() {
		return mixDesignCode;
	}

	public void setMixDesignCode(String mixDesignCode) {
		this.mixDesignCode = mixDesignCode;
	}

	public Long getMixDesignParameterId() {
		return mixDesignParameterId;
	}

	public void setMixDesignParameterId(Long mixDesignParameterId) {
		this.mixDesignParameterId = mixDesignParameterId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
