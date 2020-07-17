package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "parameter_equation_element")
public class ParameterEquationElement extends DateAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "parameterEquationId", nullable = false)
	private ParameterEquation parameterEquation;
	@ManyToOne
	@JoinColumn(name = "testParameterId", nullable = false)
	private TestParameter testParameter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParameterEquation getParameterEquation() {
		return parameterEquation;
	}

	public void setParameterEquation(ParameterEquation parameterEquation) {
		this.parameterEquation = parameterEquation;
	}

	public TestParameter getTestParameter() {
		return testParameter;
	}

	public void setTestParameter(TestParameter testParameter) {
		this.testParameter = testParameter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
