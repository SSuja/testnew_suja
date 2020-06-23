package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "mix_design_parameter_result")
public class MixDesignParameterResult implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "mixDesignCode", nullable = false)
	private MixDesign mixDesign;
	@ManyToOne
	@JoinColumn(name = "mixDesignParameterId", nullable = false)
	private MixDesignParameter mixDesignParameter;
	private double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MixDesign getMixDesign() {
		return mixDesign;
	}

	public void setMixDesign(MixDesign mixDesign) {
		this.mixDesign = mixDesign;
	}

	public MixDesignParameter getMixDesignParameter() {
		return mixDesignParameter;
	}

	public void setMixDesignParameter(MixDesignParameter mixDesignParameter) {
		this.mixDesignParameter = mixDesignParameter;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
