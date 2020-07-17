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
@Table(schema = "tokyo-supermix", name = "material_quality_parameter")
public class MaterialQualityParameter extends DateAudit implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "qualityParameterId", nullable = false)
  private QualityParameter qualityParameter;
  private Double value;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = false)
  private RawMaterial rawMaterial;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public QualityParameter getQualityParameter() {
    return qualityParameter;
  }

  public void setQualityParameter(QualityParameter qualityParameter) {
    this.qualityParameter = qualityParameter;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }
}
