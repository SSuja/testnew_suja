package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.ConcreteStatus;

@Entity
@Table(schema = "tokyo-supermix", name = "concrete_test_status")
public class ConcreteTestStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "concreteTestTypeId", nullable = false)
	private ConcreteTestType concreteTestType;
	@ManyToOne
	@JoinColumn(name = "finishProductSampleId", nullable = false)
	private FinishProductSample finishProductSample;
	@Enumerated(EnumType.ORDINAL)
	private ConcreteStatus concreteStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConcreteTestType getConcreteTestType() {
		return concreteTestType;
	}

	public void setConcreteTestType(ConcreteTestType concreteTestType) {
		this.concreteTestType = concreteTestType;
	}

	public FinishProductSample getFinishProductSample() {
		return finishProductSample;
	}

	public void setFinishProductSample(FinishProductSample finishProductSample) {
		this.finishProductSample = finishProductSample;
	}

	public ConcreteStatus getConcreteStatus() {
		return concreteStatus;
	}

	public void setConcreteStatus(ConcreteStatus concreteStatus) {
		this.concreteStatus = concreteStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
