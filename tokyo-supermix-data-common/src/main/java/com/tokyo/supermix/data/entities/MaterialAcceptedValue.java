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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.MaterialParamType;

@Entity
@Table(schema = "tokyo-supermix", name = "material_accepted_value")
public class MaterialAcceptedValue extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  @OneToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  @ManyToOne
  @JoinColumn(name = "testEquationId", nullable = true)
  private TestEquation testEquation;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = true)
  private RawMaterial rawMaterial;
  @Enumerated(EnumType.ORDINAL)
  private Condition conditionRange;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = true)
  private TestParameter testParameter;
  private boolean finalResult;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;
  @Enumerated(EnumType.ORDINAL)
  private CategoryAcceptedType categoryAcceptedType;
  @ManyToOne
  @JoinColumn(name = "materialQualityParameterId", nullable = true)
  private MaterialQualityParameter materialQualityParameter;
  @Enumerated(EnumType.ORDINAL)
  private MaterialParamType materialParamType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getMinValue() {
    return minValue;
  }

  public void setMinValue(Double minValue) {
    this.minValue = minValue;
  }

  public Double getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(Double maxValue) {
    this.maxValue = maxValue;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public TestConfigure getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigure testConfigure) {
    this.testConfigure = testConfigure;
  }

  public TestEquation getTestEquation() {
    return testEquation;
  }

  public void setTestEquation(TestEquation testEquation) {
    this.testEquation = testEquation;
  }

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }

  public TestParameter getTestParameter() {
    return testParameter;
  }

  public void setTestParameter(TestParameter testParameter) {
    this.testParameter = testParameter;
  }

  public boolean isFinalResult() {
    return finalResult;
  }

  public void setFinalResult(boolean finalResult) {
    this.finalResult = finalResult;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public MaterialSubCategory getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public CategoryAcceptedType getCategoryAcceptedType() {
    return categoryAcceptedType;
  }

  public void setCategoryAcceptedType(CategoryAcceptedType categoryAcceptedType) {
    this.categoryAcceptedType = categoryAcceptedType;
  }

  public MaterialParamType getMaterialParamType() {
    return materialParamType;
  }

  public void setMaterialParamType(MaterialParamType materialParamType) {
    this.materialParamType = materialParamType;
  }

  public MaterialQualityParameter getMaterialQualityParameter() {
    return materialQualityParameter;
  }

  public void setMaterialQualityParameter(MaterialQualityParameter materialQualityParameter) {
    this.materialQualityParameter = materialQualityParameter;
  }
}
