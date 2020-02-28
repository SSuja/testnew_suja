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
	  private Date date;
	  private String targetStrength;
	  private String waterCementRatio;
	  private String waterBinderRatio;

	  @ManyToOne
	  @JoinColumn(name = "plantCode", nullable = false)
	  private Plant plant;

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

	  public Date getDate() {
	    return date;
	  }

	public void setDate(Date date) {
	    this.date = date;
	  }

	  public String getTargetStrength() {
	    return targetStrength;
	  }

	  public void setTargetStrength(String targetStrength) {
	    this.targetStrength = targetStrength;
	  }

	  public String getWaterCementRatio() {
	    return waterCementRatio;
	  }

	  public void setWaterCementRatio(String waterCementRatio) {
	    this.waterCementRatio = waterCementRatio;
	  }

	  public String getWaterBinderRatio() {
	    return waterBinderRatio;
	  }

	  public void setWaterBinderRatio(String waterBinderRatio) {
	    this.waterBinderRatio = waterBinderRatio;
	  }

	  public Plant getPlant() {
	    return plant;
	  }

	  public void setPlant(Plant plant) {
	    this.plant = plant;
	  }

	  public static long getSerialversionuid() {
	    return serialVersionUID;
	  }

}
