package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "equipment_parameter")
public class EquipmentParameter {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String shortFormat;

	@ManyToOne
	@JoinColumn(name = "parameterMasterCode", nullable = false)
	private ParameterMaster parameterMaster;

	@ManyToOne
	@JoinColumn(name = "equipmentCode", nullable = false)
	private Equipment equipment;

	@ManyToOne
	@JoinColumn(name = "unitCode", nullable = false)
	private Unit unit;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortFormat() {
		return shortFormat;
	}

	public void setShortFormat(String shortFormat) {
		this.shortFormat = shortFormat;
	}

	public ParameterMaster getParameterMaster() {
		return parameterMaster;
	}

	public void setParameterMaster(ParameterMaster parameterMaster) {
		this.parameterMaster = parameterMaster;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
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
