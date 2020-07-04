package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample")
public class FinishProductSample extends DateAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String workOrderNo;
	private String finishProductCode;
	private Date date;
	@ManyToOne
	@JoinColumn(name = "equipmentId", nullable = false)
	private Equipment equipment;
	@ManyToOne
	@JoinColumn(name = "mixDesignCode", nullable = false)
	private MixDesign mixDesign;
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public MixDesign getMixDesign() {
		return mixDesign;
	}

	public void setMixDesign(MixDesign mixDesign) {
		this.mixDesign = mixDesign;
	}

	public String getFinishProductCode() {
		return finishProductCode;
	}

	public void setFinishProductCode(String finishProductCode) {
		this.finishProductCode = finishProductCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
