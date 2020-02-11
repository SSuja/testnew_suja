package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample")
public class FinishProductSample implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private Date dateAndTime;

	private String sampleSize;

	private String workOrderNo;

	@ManyToOne
	@JoinColumn(name = "mixDesignCode", nullable = false)
	private MixDesign mixDesign;

	@ManyToOne
	@JoinColumn(name = "projectCode", nullable = false)
	private Project project;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(String sampleSize) {
		this.sampleSize = sampleSize;
	}

	public String getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public MixDesign getMixDesign() {
		return mixDesign;
	}

	public void setMixDesign(MixDesign mixDesign) {
		this.mixDesign = mixDesign;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
