package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.enums.EntryLevel;

public class TestParameterDto {
	private Long id;
	  private Parameter parameter;
	  private String qualityParameterName;
	  private String abbreviation;
	  private EntryLevel entryLevel;
	  private Double value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Parameter getParameter() {
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public EntryLevel getEntryLevel() {
		return entryLevel;
	}
	public void setEntryLevel(EntryLevel entryLevel) {
		this.entryLevel = entryLevel;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getQualityParameterName() {
		return qualityParameterName;
	}
	public void setQualityParameterName(String qualityParameterName) {
		this.qualityParameterName = qualityParameterName;
	}
	  
	  

}
