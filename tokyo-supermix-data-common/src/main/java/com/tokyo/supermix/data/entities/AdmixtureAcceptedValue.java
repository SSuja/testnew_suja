package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance
@Table(schema = "tokyo-supermix", name = "admixture_accepted_value")
public class AdmixtureAcceptedValue extends AcceptedValue {
  private static final long serialVersionUID = 1L;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = false)
  private RawMaterial rawMaterial;

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }
}
