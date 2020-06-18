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

import com.tokyo.supermix.data.enums.TestType;

@Entity
@Table(schema = "tokyo-supermix", name = "test_configure")
public class TestConfigure implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private boolean coreTest;
  private String description;
  private String testProcedure;
  private String prefix;
  private String resultLabel;
  @Enumerated(EnumType.ORDINAL)
  private TestType testType;
  private boolean isEquationExists;
  @ManyToOne
  @JoinColumn(name = "equationId", nullable = true)
  private Equation equation;
  @ManyToOne
  @JoinColumn(name = "testId", nullable = false)
  private Test test;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = false)
  private MaterialCategory materialCategory;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MaterialCategory getMaterialCategory() {
    return materialCategory;
  }

  public void setMaterialCategory(MaterialCategory materialCategory) {
    this.materialCategory = materialCategory;
  }

  public MaterialSubCategory getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTestProcedure() {
    return testProcedure;
  }

  public void setTestProcedure(String testProcedure) {
    this.testProcedure = testProcedure;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public TestType getTestType() {
    return testType;
  }

  public void setTestType(TestType testType) {
    this.testType = testType;
  }

  public boolean isEquationExists() {
    return isEquationExists;
  }

  public void setEquationExists(boolean isEquationExists) {
    this.isEquationExists = isEquationExists;
  }

  public Equation getEquation() {
    return equation;
  }

  public void setEquation(Equation equation) {
    this.equation = equation;
  }

  public String getResultLabel() {
    return resultLabel;
  }

  public void setResultLabel(String resultLabel) {
    this.resultLabel = resultLabel;
  }
}
