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
@Table(schema = "tokyo-supermix", name = "equipment_parameter")
public class EquipmentParameter implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String shortFormat;
  @ManyToOne
  @JoinColumn(name = "parameterId", nullable = false)
  private Parameter parameter;
  @ManyToOne
  @JoinColumn(name = "equipmentId", nullable = false)
  private Equipment equipment;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShortFormat() {
    return shortFormat;
  }

  public void setShortFormat(String shortFormat) {
    this.shortFormat = shortFormat;
  }

  public Parameter getParameter() {
    return parameter;
  }

  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
  }

  public Equipment getEquipment() {
    return equipment;
  }

  public void setEquipment(Equipment equipment) {
    this.equipment = equipment;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
