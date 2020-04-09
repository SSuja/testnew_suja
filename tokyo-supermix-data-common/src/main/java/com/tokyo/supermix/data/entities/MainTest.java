package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "main_test")
public class MainTest implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
