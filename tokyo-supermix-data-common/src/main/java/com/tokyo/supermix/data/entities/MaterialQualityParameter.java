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
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;

@Entity
@Table(schema = "tokyo-supermix", name = "marial_quality_parameter")
public class MaterialQualityParameter extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  @Enumerated(EnumType.ORDINAL)
  private Condition conditionRange;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = true)
  private RawMaterial rawMaterial;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = true)
  private MaterialCategory materialCategory;
  @ManyToOne
  @JoinColumn(name = "qualityParameterId", nullable = false)
  private QualityParameter qualityParameter;
  @Enumerated(EnumType.ORDINAL)
  private QualityParamaterType qualityParamaterType;

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

  public Condition getConditionRange() {
    return conditionRange;
  }

  public void setConditionRange(Condition conditionRange) {
    this.conditionRange = conditionRange;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public MaterialSubCategory getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public MaterialCategory getMaterialCategory() {
    return materialCategory;
  }

  public void setMaterialCategory(MaterialCategory materialCategory) {
    this.materialCategory = materialCategory;
  }

  public QualityParameter getQualityParameter() {
    return qualityParameter;
  }

  public void setQualityParameter(QualityParameter qualityParameter) {
    this.qualityParameter = qualityParameter;
  }

  public QualityParamaterType getQualityParamaterType() {
    return qualityParamaterType;
  }

  public void setQualityParamaterType(QualityParamaterType qualityParamaterType) {
    this.qualityParamaterType = qualityParamaterType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
