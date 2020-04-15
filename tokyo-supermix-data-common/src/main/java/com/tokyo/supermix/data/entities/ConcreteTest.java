package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.ConcreteType;

@Entity
@Table(schema = "tokyo-supermix", name = "concrete_test")
public class ConcreteTest implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @Enumerated(EnumType.ORDINAL)
  private ConcreteType type;

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

  public ConcreteType getType() {
    return type;
  }

  public void setType(ConcreteType type) {
    this.type = type;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
