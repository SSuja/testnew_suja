package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "material_quantity")
public class MaterialQuantity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	private String measurement;

	private String quantity;

	@ManyToOne
	@JoinColumn(name = "processSampleCode", nullable = false)
	private ProcessSample processSample;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
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

	public ProcessSample getProcessSample() {
		return processSample;
	}

	public void setProcessSample(ProcessSample processSample) {
		this.processSample = processSample;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
