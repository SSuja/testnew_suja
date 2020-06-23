package com.tokyo.supermix.data.dto;

public class MixDesignParameterResponseDto {
	private Long id;
	private String name;
	private EquationResponseDto equation;
	private boolean equationExists;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EquationResponseDto getEquation() {
		return equation;
	}

	public void setEquation(EquationResponseDto equation) {
		this.equation = equation;
	}

	public boolean isEquationExists() {
		return equationExists;
	}

	public void setEquationExists(boolean equationExists) {
		this.equationExists = equationExists;
	}	
}
