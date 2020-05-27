package com.tokyo.supermix.data.dto;

public class ConcreteTestRequestDto {
	private Long id;
	private String name;
	private Long concreteTestTypeId;

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

	public Long getConcreteTestTypeId() {
		return concreteTestTypeId;
	}

	public void setConcreteTestTypeId(Long concreteTestTypeId) {
		this.concreteTestTypeId = concreteTestTypeId;
	}
}
