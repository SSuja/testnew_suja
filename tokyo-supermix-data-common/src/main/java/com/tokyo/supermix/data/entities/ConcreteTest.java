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
@Table(schema = "tokyo-supermix", name = "concrete_test")
public class ConcreteTest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "concreteTestTypeId", nullable = false)
	private ConcreteTestType concreteTestType;

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

	public ConcreteTestType getConcreteTestType() {
		return concreteTestType;
	}

	public void setConcreteTestType(ConcreteTestType concreteTestType) {
		this.concreteTestType = concreteTestType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
