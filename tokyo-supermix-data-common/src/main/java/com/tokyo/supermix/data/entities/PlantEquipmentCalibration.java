package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.CalibrationType;

@Entity
@Table(schema = "tokyo-supermix", name = "plant_equipment_calibration")
public class PlantEquipmentCalibration implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date calibratedDate;
  private Date dueDate;
  @Enumerated(EnumType.ORDINAL)
  private CalibrationType calibrationType;
  @ManyToOne
  @JoinColumn(name = "userId", nullable = true)
  private User user;
  private String description;
  @ManyToOne
  @JoinColumn(name = "plantEquipmentId", nullable = false)
  private PlantEquipment plantEquipment;
  @ManyToOne
  @JoinColumn(name = "supplierId", nullable = true)
  private Supplier supplier;
  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCalibratedDate() {
    return calibratedDate;
  }

  public void setCalibratedDate(Date calibratedDate) {
    this.calibratedDate = calibratedDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public CalibrationType getCalibrationType() {
    return calibrationType;
  }

  public void setCalibrationType(CalibrationType calibrationType) {
    this.calibrationType = calibrationType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PlantEquipment getPlantEquipment() {
    return plantEquipment;
  }

  public void setPlantEquipment(PlantEquipment plantEquipment) {
    this.plantEquipment = plantEquipment;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
