package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample_issue")
public class FinishProductSampleIssue extends DateAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String code;
	private String truckNo;
	@ManyToOne
	@JoinColumn(name = "finishProductSampleCode", nullable = false)
	private FinishProductSample finishProductSample;
	@ManyToOne
	@JoinColumn(name = "projectCode", nullable = false)
	private Project project;
	@OneToOne
	@JoinColumn(name = "pourId", nullable = true)
	private Pour pour;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTruckNo() {
		return truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}

	public FinishProductSample getFinishProductSample() {
		return finishProductSample;
	}

	public void setFinishProductSample(FinishProductSample finishProductSample) {
		this.finishProductSample = finishProductSample;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Pour getPour() {
		return pour;
	}

	public void setPour(Pour pour) {
		this.pour = pour;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
