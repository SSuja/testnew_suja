package com.tokyo.supermix.data.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "load_material")
public class LoadMaterial {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String vehicleNo;

	private Date dateAndTime;

	private String measurement;

	private String quantity;

	@ManyToOne
	@JoinColumn(name = "incomingSampleCode", nullable = false)
	private IncomingSample incomingSample;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public IncomingSample getIncomingSample() {
		return incomingSample;
	}

	public void setIncomingSample(IncomingSample incomingSample) {
		this.incomingSample = incomingSample;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
