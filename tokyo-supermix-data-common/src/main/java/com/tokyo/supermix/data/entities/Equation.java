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
@Table(schema = "tokyo-supermix", name = "equation")
public class Equation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String formula;
	@OneToOne
	@JoinColumn(name = "testConfigureId", nullable = false)
	private TestConfigure testConfigure;

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

	public TestConfigure getTestConfigure() {
		return testConfigure;
	}

	public void setTestConfigure(TestConfigure testConfigure) {
		this.testConfigure = testConfigure;
	}
}
