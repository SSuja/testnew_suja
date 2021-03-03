package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
}
