package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "material_accepted_value")
public class MaterialAcceptedValue {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Double minValue;
	private Double maxValue;
	@OneToOne
	@JoinColumn(name = "testConfigureId", nullable = false)
	private TestConfigure testConfigure;
	@ManyToOne
	@JoinColumn(name = "unitId", nullable = false)
	private Unit unit;
	@ManyToOne
	@JoinColumn(name = "rawMaterialId", nullable = false)
	private RawMaterial rawMaterial;

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public TestConfigure getTestConfigure() {
		return testConfigure;
	}

	public void setTestConfigure(TestConfigure testConfigure) {
		this.testConfigure = testConfigure;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
