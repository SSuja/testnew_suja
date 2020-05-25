package com.tokyo.supermix.data.dto;

public class ConcreteTestResponseDto {
	private Long id;
	private String name;
	private ConcreteTestTypeDto ConcreteTestType;

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

	public ConcreteTestTypeDto getConcreteTestType() {
		return ConcreteTestType;
	}

	public void setConcreteTestType(ConcreteTestTypeDto concreteTestType) {
		ConcreteTestType = concreteTestType;
	}
}
