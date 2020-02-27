package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "mix_design")
public class MixDesign implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;
	private String grade;
	private Date dateAndTime;
	@ManyToOne
	@JoinColumn(name = "plantCode", nullable = false)
	private Plant plant;
	@ManyToOne
	@JoinColumn(name = "materialCode", nullable = false)
	private RawMaterial material;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	public RawMaterial getMaterial() {
		return material;
	}

	public void setMaterial(RawMaterial material) {
		this.material = material;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
