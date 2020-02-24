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
@Table(schema = "tokyo-supermix", name = "material_parameter")
public class MaterialParameter implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String minimumValue;
  private String maximumValue;
  private String name;
  private String shortFormat;
  private String parameterAdditional;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = false)
  private MaterialCategory materialCategory;
  @ManyToOne
  @JoinColumn(name = "parameterId", nullable = false)
  private Parameter parameter;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMinimumValue() {
    return minimumValue;
  }

  public void setMinimumValue(String minimumValue) {
    this.minimumValue = minimumValue;
  }

  public String getMaximumValue() {
    return maximumValue;
  }

  public void setMaximumValue(String maximumValue) {
    this.maximumValue = maximumValue;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MaterialCategory getMaterialCategory() {
    return materialCategory;
  }

  public void setMaterialCategory(MaterialCategory materialCategory) {
    this.materialCategory = materialCategory;
  }

  public Parameter getParameter() {
    return parameter;
  }

  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public String getShortFormat() {
    return shortFormat;
  }

  public void setShortFormat(String shortFormat) {
    this.shortFormat = shortFormat;
  }

  public String getParameterAdditional() {
    return parameterAdditional;
  }

  public void setParameterAdditional(String parameterAdditional) {
    this.parameterAdditional = parameterAdditional;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
