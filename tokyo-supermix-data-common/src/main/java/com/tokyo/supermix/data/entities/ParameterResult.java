package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "parameter_result")
public class ParameterResult extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double value;
  @ManyToOne
  @JoinColumn(name = "materialTestTrialCode", nullable = false)
  private MaterialTestTrial materialTestTrial;
  @ManyToOne
  @JoinColumn(name = "materialTestCode", nullable = false)
  private MaterialTest materialTest;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = false)
  private TestParameter testParameter;
  @ManyToOne
  @JoinColumn(name = "testEquationId", nullable = true)
  private TestEquation testEquation;
  private LocalDateTime dateValue;

  public LocalDateTime getDateValue() {
    return dateValue;
  }

  public void setDateValue(LocalDateTime dateValue) {
    this.dateValue = dateValue;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public MaterialTestTrial getMaterialTestTrial() {
    return materialTestTrial;
  }

  public void setMaterialTestTrial(MaterialTestTrial materialTestTrial) {
    this.materialTestTrial = materialTestTrial;
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
}
