package com.tokyo.supermix.data.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "mix_design")
public class MixDesign {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String actualGrade;

	private String expectedGrade;

	private Date dateAndTime;

	@ManyToOne
	@JoinColumn(name = "plantCode", nullable = false)
	private Plant plant;

	@ManyToOne
	@JoinColumn(name = "materialQuantityCode", nullable = false)
	private MaterialQuantity materialQuantity;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getActualGrade() {
		return actualGrade;
	}

	public void setActualGrade(String actualGrade) {
		this.actualGrade = actualGrade;
	}

	public String getExpectedGrade() {
		return expectedGrade;
	}

	public void setExpectedGrade(String expectedGrade) {
		this.expectedGrade = expectedGrade;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public MaterialQuantity getMaterialQuantity() {
		return materialQuantity;
	}

	public void setMaterialQuantity(MaterialQuantity materialQuantity) {
		this.materialQuantity = materialQuantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
