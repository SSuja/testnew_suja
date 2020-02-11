package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "pour")
public class Pour implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String number;
	private String description;
	@OneToOne
	@JoinColumn(name = "finishProductSampleCode", nullable = false)
	private FinishProductSample finishProductSample;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FinishProductSample getFinishProductSample() {
		return finishProductSample;
	}

	public void setFinishProductSample(FinishProductSample finishProductSample) {
		this.finishProductSample = finishProductSample;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
