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
@Table(schema = "tokyo-supermix", name = "test_configure")
public class TestConfigure implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean coreTest;
	private String name;
	@ManyToOne
	@JoinColumn(name = "testTypeId", nullable = false)
	private TestType testType;
	@ManyToOne
	@JoinColumn(name = "equationId", nullable = true)
	private Equation equation;
	@ManyToOne
	@JoinColumn(name = "testId", nullable = false)
	private Test test;

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

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public Equation getEquation() {
		return equation;
	}

	public void setEquation(Equation equation) {
		this.equation = equation;
	}

	public boolean isCoreTest() {
		return coreTest;
	}

	public void setCoreTest(boolean coreTest) {
		this.coreTest = coreTest;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
