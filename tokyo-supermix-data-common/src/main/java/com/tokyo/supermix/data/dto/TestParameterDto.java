package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.enums.EntryLevel;

public class TestParameterDto {
	private Long id;
	  private String parameterName;
	  private String qualityParameterName;
	  private String abbreviation;
	  private EntryLevel entryLevel;
	  private Double value;
	  private UnitDto unit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	
	
	public UnitDto getUnit() {
		return unit;
	}
	public void setUnit(UnitDto unit) {
		this.unit = unit;
	}
	  
	  

}
