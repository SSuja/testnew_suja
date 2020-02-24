package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "test")
public class Test implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String testName;
  private String description;
  private String procedure;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = false)
  private MaterialSubCategory materialSubCategory;
  @ManyToOne
  @JoinColumn(name = "materialParameterId", nullable = false)
  private MaterialParameter materialParameter;
  @ManyToOne
  @JoinColumn(name = "equationConfigurationId", nullable = false)
  private EquationConfiguration equationConfiguration;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getProcedure() {
    return procedure;
  }

  public void setProcedure(String procedure) {
    this.procedure = procedure;
  }

  public MaterialSubCategory getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public MaterialParameter getMaterialParameter() {
    return materialParameter;
  }

  public void setMaterialParameter(MaterialParameter materialParameter) {
    this.materialParameter = materialParameter;
  }

  public EquationConfiguration getEquationConfiguration() {
    return equationConfiguration;
  }

  public void setEquationConfiguration(EquationConfiguration equationConfiguration) {
    this.equationConfiguration = equationConfiguration;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
