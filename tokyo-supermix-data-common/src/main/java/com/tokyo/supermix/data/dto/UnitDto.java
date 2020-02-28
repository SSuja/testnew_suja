package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UnitDto {

	private Long id;
	@NotNull(message = "{unitDto.unit.null}")
	@NotEmpty(message = "{unitDto.unit.empty}")
	@Pattern(regexp = "^[a-zA-Z\\\\s]+$", message = "{unitDto.unit.specialCharacter}")
	private String unit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
