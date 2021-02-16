package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "plant_equipment")
public class PlantEquipment extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String serialNo;
  private String brandName;
  private String modelName;
  private String description;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;
  @ManyToOne
  @JoinColumn(name = "equipmentId", nullable = false)
  private Equipment equipment;
  private boolean calibrationExists;
  private boolean sentMail;

  public String getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public Equipment getEquipment() {
    return equipment;
  }

  public void setEquipment(Equipment equipment) {
    this.equipment = equipment;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public boolean isCalibrationExists() {
    return calibrationExists;
  }

  public void setCalibrationExists(boolean calibrationExists) {
    this.calibrationExists = calibrationExists;
  }

  public boolean isSentMail() {
    return sentMail;
  }

  public void setSentMail(boolean sentMail) {
    this.sentMail = sentMail;
  }
}
