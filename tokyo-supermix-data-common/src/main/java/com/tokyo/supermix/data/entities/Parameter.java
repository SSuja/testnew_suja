package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.ParameterType;

@Entity
@Table(schema = "tokyo-supermix", name = "parameter")
public class Parameter implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private ParameterType parameterType;

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

  public ParameterType getParameterType() {
    return parameterType;
  }

  public void setParameterType(ParameterType parameterType) {
    this.parameterType = parameterType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
