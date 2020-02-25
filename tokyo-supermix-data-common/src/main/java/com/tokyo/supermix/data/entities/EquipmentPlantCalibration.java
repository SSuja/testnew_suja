package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "equipment_plant_calibration")
public class EquipmentPlantCalibration implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date calibratedDate;
	private Date dueDate;
	private String calibratedBy;
	private String testerName;
	private String description;
	@ManyToOne
	@JoinColumn(name = "equipmentPlantId", nullable = false)
	private EquipmentPlant equipmentPlant;
	@ManyToOne
	@JoinColumn(name = "supplierId", nullable = false)
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

	public String getCalibratedBy() {
		return calibratedBy;
	}

	public void setCalibratedBy(String calibratedBy) {
		this.calibratedBy = calibratedBy;
	}

	public String getTesterName() {
		return testerName;
	}

	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EquipmentPlant getEquipmentPlant() {
		return equipmentPlant;
	}

	public void setEquipmentPlant(EquipmentPlant equipmentPlant) {
		this.equipmentPlant = equipmentPlant;
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
}
