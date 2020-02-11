package com.tokyo.supermix.data.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "equipment_calibration")
public class EquipmentCalibration {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	private String calibratedBy;

	private Date calibratedDate;

	private String companyName;

	private String description;

	private Date dueDate;

	private String testerName;

	@ManyToOne
	@JoinColumn(name = "equipmentCalibrationCode", nullable = false)
	private EquipmentCalibration equipmentCalibration;

	@ManyToOne
	@JoinColumn(name = "plantCode", nullable = false)
	private Plant plant;

	@ManyToOne
	@JoinColumn(name = "supplierCode", nullable = false)
	private Supplier supplier;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getCalibratedBy() {
		return calibratedBy;
	}

	public void setCalibratedBy(String calibratedBy) {
		this.calibratedBy = calibratedBy;
	}

	public Date getCalibratedDate() {
		return calibratedDate;
	}

	public void setCalibratedDate(Date calibratedDate) {
		this.calibratedDate = calibratedDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getTesterName() {
		return testerName;
	}

	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}

	public EquipmentCalibration getEquipmentCalibration() {
		return equipmentCalibration;
	}

	public void setEquipmentCalibration(EquipmentCalibration equipmentCalibration) {
		this.equipmentCalibration = equipmentCalibration;
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

}
