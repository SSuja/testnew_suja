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
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.CalibrationType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
  @JoinColumn(name = "userId", nullable = false)
  private User user;
  private String description;
  @ManyToOne
  @JoinColumn(name = "plantEquipmentSerialNo", nullable = false)
  private PlantEquipment plantEquipment;
  @ManyToOne
  @JoinColumn(name = "supplierId", nullable = true)
  private Supplier supplier;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @ManyToOne
  @JoinColumn(name = "employeeId", nullable = true)
  private Employee employee;
  private String accuracy;
  private Long noOfDays;
  private boolean sentMail;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = true)
  private Unit unit;
}
