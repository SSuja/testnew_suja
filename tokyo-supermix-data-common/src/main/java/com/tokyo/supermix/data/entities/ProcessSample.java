package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "process_sample")
public class ProcessSample extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Long quantity;
  @OneToOne
  @JoinColumn(name = "incomingSampleCode", nullable = false)
  private IncomingSample incomingSample;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = true)
  private RawMaterial rawMaterial;

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public IncomingSample getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSample incomingSample) {
    this.incomingSample = incomingSample;
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
