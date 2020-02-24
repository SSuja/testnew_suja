package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MaterialState implements Serializable {
  
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String materialState;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMaterialState() {
    return materialState;
  }

  public void setMaterialState(String materialState) {
    this.materialState = materialState;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
