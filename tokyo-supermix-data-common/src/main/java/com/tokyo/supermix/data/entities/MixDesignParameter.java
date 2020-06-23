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
@Table(schema = "tokyo-supermix", name = "mix_design_parameter")
public class MixDesignParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToOne
	@JoinColumn(name = "equationId", nullable = true)
	private Equation equation;

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

	public Equation getEquation() {
		return equation;
	}

	public void setEquation(Equation equation) {
		this.equation = equation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
