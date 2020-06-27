package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tokyo.supermix.data.enums.EquationName;
import com.tokyo.supermix.data.enums.EquationType;

public class EquationRequestDto {
	private Long id;
	@NotNull(message = "{equationDto.formula.null}")
	@NotEmpty(message = "{equationDto.formula.empty}")
	private String formula;
	@NotNull(message = "{equationDto.name.null}")
	@NotEmpty(message = "{equationDto.name.empty}")
	private EquationName name;
	private EquationType equationType;
	private boolean parameterExists;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	public EquationName getName() {
		return name;
	}

	public void setName(EquationName name) {
		this.name = name;
	}

	public EquationType getEquationType() {
		return equationType;
	}

	public void setEquationType(EquationType equationType) {
		this.equationType = equationType;
	}

	public boolean isParameterExists() {
		return parameterExists;
	}

	public void setParameterExists(boolean parameterExists) {
		this.parameterExists = parameterExists;
	}
}
