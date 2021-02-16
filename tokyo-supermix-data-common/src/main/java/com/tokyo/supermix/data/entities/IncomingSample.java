package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "incoming_sample")
public class IncomingSample extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String vehicleNo;
  private Date date;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = false)
  private RawMaterial rawMaterial;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;
  @ManyToOne
  @JoinColumn(name = "supplierId", nullable = false)
  private Supplier supplier;
  @Enumerated(EnumType.ORDINAL)
  private RawMaterialSampleType rawMaterialSampleType;
  @ManyToOne
	@JoinColumn(name = "userId", nullable = true)
	private User user;
  private boolean sentMail;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getVehicleNo() {
    return vehicleNo;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public RawMaterialSampleType getRawMaterialSampleType() {
    return rawMaterialSampleType;
  }

  public void setRawMaterialSampleType(RawMaterialSampleType rawMaterialSampleType) {
    this.rawMaterialSampleType = rawMaterialSampleType;
  }

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public boolean isSentMail() {
  return sentMail;
}

public void setSentMail(boolean sentMail) {
  this.sentMail = sentMail;
}
  
}
