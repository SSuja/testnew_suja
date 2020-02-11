package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "additional_parameter")
public class AdditionalParameter {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String name;

	private String shortFormat;

	@ManyToOne
	@JoinColumn(name = "unitCode", nullable = false)
	private Unit unit;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortFormat() {
		return shortFormat;
	}

	public void setShortFormat(String shortFormat) {
		this.shortFormat = shortFormat;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
