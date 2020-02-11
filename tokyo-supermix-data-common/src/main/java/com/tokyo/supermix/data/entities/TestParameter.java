package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "test_parameter")
public class TestParameter {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@ManyToOne
	@JoinColumn(name = "parameterMasterCode", nullable = false)
	private ParameterMaster parameterMaster;

	@ManyToOne
	@JoinColumn(name = "unitCode", nullable = false)
	private Unit unit;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ParameterMaster getParameterMaster() {
		return parameterMaster;
	}

	public void setParameterMaster(ParameterMaster parameterMaster) {
		this.parameterMaster = parameterMaster;
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
