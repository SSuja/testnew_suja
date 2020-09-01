package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "material_test_result")
public class MaterialTestResult {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "materialTestCode", nullable = false)
  private MaterialTest materialTest;
  @ManyToOne
  @JoinColumn(name = "testEquationId", nullable = true)
  private TestEquation testEquation;
  private Double result;
  @ManyToOne
  @JoinColumn(name = "testParamterId", nullable = true)
  private TestParameter testParameter;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MaterialTest getMaterialTest() {
    return materialTest;
  }

  public void setMaterialTest(MaterialTest materialTest) {
    this.materialTest = materialTest;
  }

  public TestEquation getTestEquation() {
    return testEquation;
  }

  public void setTestEquation(TestEquation testEquation) {
    this.testEquation = testEquation;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public TestParameter getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameter testParameter) {
    this.testParameter = testParameter;
  }
}
