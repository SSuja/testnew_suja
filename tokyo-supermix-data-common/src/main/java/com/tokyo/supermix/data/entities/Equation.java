package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tokyo.supermix.data.enums.EquationType;

@Entity
@Table(schema = "tokyo-supermix", name = "equation")
public class Equation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String formula;
	private String name;
	@Enumerated(EnumType.ORDINAL)
	private EquationType equationType;
	private boolean isParameter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EquationType getEquationType() {
		return equationType;
	}

	public void setEquationType(EquationType equationType) {
		this.equationType = equationType;
	}

	public boolean isParameter() {
		return isParameter;
	}

	public void setParameter(boolean isParameter) {
		this.isParameter = isParameter;
	}
}
