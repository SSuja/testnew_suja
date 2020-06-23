package com.tokyo.supermix.data.dto;

public class MixDesignParameterRequestDto {
	private Long id;
	private String name;
	private Long equationId;
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

	public Long getEquationId() {
		return equationId;
	}

	public void setEquationId(Long equationId) {
		this.equationId = equationId;
	}

	public boolean isEquationExists() {
		return equationExists;
	}

	public void setEquationExists(boolean equationExists) {
		this.equationExists = equationExists;
	}
}
