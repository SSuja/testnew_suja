package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@Table(schema = "tokyo-supermix", name = "admixture_accepted_value")
public class AdmixtureAcceptedValue extends AcceptedValue implements Serializable{
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
