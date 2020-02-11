package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "process_sample")
public class ProcessSample implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;
	private Date expireDate;
	private Date dateAndTime;
	private Boolean status;
	private String totalLoad;
	@ManyToOne
	@JoinColumn(name = "materialLoadCode", nullable = false)
	private MaterialLoad materialLoad;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTotalLoad() {
		return totalLoad;
	}

	public void setTotalLoad(String totalLoad) {
		this.totalLoad = totalLoad;
	}

	public MaterialLoad getMaterialLoad() {
		return materialLoad;
	}

	public void setMaterialLoad(MaterialLoad materialLoad) {
		this.materialLoad = materialLoad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
